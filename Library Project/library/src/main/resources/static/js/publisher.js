// publisher.js - مدیریت صفحه ناشران

// ============================
// متغیرهای سراسری
// ============================

let allPublisherTypes = [];
let isEditMode = false;
let searchTimeout = null;

// ============================
// توابع مدیریت انواع ناشر
// ============================

function loadPublisherTypes() {
    fetch('/publisher/findAllPublisherTypes')
        .then(r => {
            if (!r.ok) throw new Error('خطا در دریافت لیست انواع ناشر');
            return r.json();
        })
        .then(data => {
            allPublisherTypes = data;
            displayPublisherTypes(data);
        })
        .catch(err => console.error('Error loading publisher types:', err));
}

function displayPublisherTypes(types) {
    const tbody = document.getElementById('publisherTypeTableBody');
    if (!tbody) return;

    let html = '';
    if (!types || types.length === 0) {
        html = '<tr><td colspan="3" style="text-align:center;color:#888;">موردی یافت نشد</td></tr>';
    } else {
        types.forEach(t => {
            const name = t.publisherTypeName || t.typeName || t.name || '';
            const id = t.publisherTypeId || t.id || '';
            html += `
                <tr onclick="selectPublisherType('${id}','${name.replace(/'/g, "\\'")}')">
                    <td>${id}</td>
                    <td>${name}</td>
                    <td><button class="btn btn-success btn-sm">انتخاب</button></td>
                </tr>
            `;
        });
    }
    tbody.innerHTML = html;
}

function selectPublisherType(id, name) {
    document.getElementById('publisherTypeId').value = id;
    const infoDiv = document.getElementById('selectedTypeInfo');
    infoDiv.classList.add('show');
    document.getElementById('selectedTypeName').textContent = name;
    closePublisherTypeSearch();
    showMessage('نوع ناشر با موفقیت انتخاب شد', 'success');
}

function openPublisherTypeSearch() {
    const modal = document.getElementById('publisherTypeSearchModal');
    modal.style.display = 'block';

    if (!allPublisherTypes.length) {
        loadPublisherTypes();
    } else {
        displayPublisherTypes(allPublisherTypes);
    }

    setTimeout(() => {
        document.getElementById('typeSearchInput').focus();
    }, 100);
}

function closePublisherTypeSearch() {
    document.getElementById('publisherTypeSearchModal').style.display = 'none';
}

function searchPublisherTypes() {
    const term = document.getElementById('typeSearchInput').value.toLowerCase().trim();
    if (term === '') {
        displayPublisherTypes(allPublisherTypes);
        return;
    }

    const filtered = allPublisherTypes.filter(t => {
        const name = (t.publisherTypeName || t.typeName || t.name || '').toLowerCase();
        return name.includes(term);
    });
    displayPublisherTypes(filtered);
}

function clearTypeSearch() {
    document.getElementById('typeSearchInput').value = '';
    displayPublisherTypes(allPublisherTypes);
    document.getElementById('typeSearchInput').focus();
}

// ============================
// توابع مدیریت مودال
// ============================

function openCreateModal() {
    const modal = document.getElementById('publisherModal');
    const title = document.getElementById('modalTitle');
    const form = document.getElementById('publisherForm');

    if (!modal || !title || !form) {
        console.error('عناصر مودال یافت نشدند');
        return;
    }

    title.textContent = 'ثبت ناشر جدید';
    form.reset();
    document.getElementById('publisherId').value = '';
    document.getElementById('selectedTypeInfo').classList.remove('show');
    isEditMode = false;

    // تغییر اکشن فرم برای ذخیره
    form.action = '/publisher/savePublisher';

    modal.style.display = 'block';

    setTimeout(() => {
        document.getElementById('name').focus();
    }, 100);
}

function openEditModal(button) {
    if (!button) {
        console.error('دکمه ویرایش یافت نشد');
        return;
    }

    const row = button.closest('tr');
    if (!row) {
        console.error('ردیف مربوطه یافت نشد');
        return;
    }

    const id = row.querySelector('td:first-child').textContent.trim();

    if (!id) {
        showMessage('شناسه ناشر معتبر نیست', 'error');
        return;
    }

    // نمایش وضعیت بارگذاری
    showMessage('در حال بارگذاری اطلاعات ناشر...', 'info');

    fetch(`/publisher/findPublisherById/${id}`)
        .then(r => {
            if (!r.ok) throw new Error('خطا در دریافت اطلاعات ناشر');
            return r.json();
        })
        .then(p => {
            const modal = document.getElementById('publisherModal');
            const title = document.getElementById('modalTitle');
            const form = document.getElementById('publisherForm');

            if (!modal || !title || !form) {
                console.error('عناصر مودال یافت نشدند');
                return;
            }

            title.textContent = 'ویرایش ناشر';

            // پر کردن فرم
            document.getElementById('publisherId').value = p.publisherId || '';
            document.getElementById('name').value = p.name || '';
            document.getElementById('code').value = p.code || '';
            document.getElementById('city').value = p.city || '';
            document.getElementById('country').value = p.country || '';
            document.getElementById('address').value = p.address || '';
            document.getElementById('phone').value = p.phone || '';

            // تنظیم نوع ناشر
            const typeId = p.publisherTypeId || p.publisherType?.publisherTypeId || '';
            const typeName = p.publisherTypeName || p.publisherType?.publisherTypeName || '';

            document.getElementById('publisherTypeId').value = typeId;

            if (typeName) {
                document.getElementById('selectedTypeName').textContent = typeName;
                document.getElementById('selectedTypeInfo').classList.add('show');
            } else {
                document.getElementById('selectedTypeInfo').classList.remove('show');
            }

            isEditMode = true;

            // تغییر اکشن فرم برای آپدیت
            form.action = '/publisher/updatePublisher';

            modal.style.display = 'block';
            showMessage('اطلاعات ناشر بارگذاری شد', 'success');
        })
        .catch(err => {
            console.error('Error loading publisher:', err);
            showMessage('خطا در بارگذاری اطلاعات ناشر: ' + err.message, 'error');
        });
}

function closeModal() {
    document.getElementById('publisherModal').style.display = 'none';
}

// ============================
// اعتبارسنجی فرم
// ============================

function validatePublisherForm() {
    const name = document.getElementById('name');
    const code = document.getElementById('code');
    const city = document.getElementById('city');
    const country = document.getElementById('country');
    const typeId = document.getElementById('publisherTypeId');

    if (!name.value.trim()) {
        showMessage('لطفاً نام ناشر را وارد کنید', 'error');
        name.focus();
        return false;
    }

    if (!code.value.trim()) {
        showMessage('لطفاً کد ناشر را وارد کنید', 'error');
        code.focus();
        return false;
    }

    if (code.value.trim().length > 5) {
        showMessage('کد ناشر باید حداکثر ۵ کاراکتر باشد', 'error');
        code.focus();
        return false;
    }

    if (!city.value.trim()) {
        showMessage('لطفاً شهر را وارد کنید', 'error');
        city.focus();
        return false;
    }

    if (!country.value.trim()) {
        showMessage('لطفاً کشور را وارد کنید', 'error');
        country.focus();
        return false;
    }

    if (!typeId.value) {
        showMessage('لطفاً نوع ناشر را انتخاب کنید', 'error');
        typeId.focus();
        return false;
    }

    return true;
}

// ============================
// ارسال فرم با AJAX
// ============================

function submitPublisherForm(event) {
    if (event) {
        event.preventDefault();
    }

    if (!validatePublisherForm()) {
        return false;
    }

    const form = document.getElementById('publisherForm');
    const formData = new FormData(form);
    const data = {};

    formData.forEach((value, key) => {
        data[key] = value;
    });

    const id = document.getElementById('publisherId').value;
    const url = id ? '/publisher/updatePublisher' : '/publisher/savePublisher';
    const method = id ? 'PUT' : 'POST';

    // نمایش وضعیت بارگذاری
    showMessage('در حال ذخیره اطلاعات...', 'info');

    fetch(url, {
        method: method,
        headers: {
            'Content-Type': 'application/json',
        },
        body: JSON.stringify(data)
    })
        .then(r => {
            if (!r.ok) throw new Error('خطا در ذخیره اطلاعات');
            return r.json();
        })
        .then(data => {
            closeModal();
            showMessage('ناشر با موفقیت ذخیره شد', 'success');
            setTimeout(() => {
                location.reload();
            }, 1500);
        })
        .catch(err => {
            console.error('Error saving publisher:', err);
            showMessage('خطا در ذخیره اطلاعات: ' + err.message, 'error');
        });
}

// ============================
// توابع جستجو (اختیاری)
// ============================

function searchPublishers(event) {
    if (event) {
        event.preventDefault();
    }

    const form = document.getElementById('searchForm');
    if (!form) {
        showMessage('فرم جستجو یافت نشد', 'error');
        return;
    }

    const formData = new FormData(form);
    const searchData = {};

    formData.forEach((value, key) => {
        if (value && value.trim() !== '') {
            searchData[key] = value.trim();
        }
    });

    // نمایش وضعیت بارگذاری
    const tableBody = document.querySelector('table tbody');
    if (tableBody) {
        tableBody.innerHTML = `<tr><td colspan="11" style="text-align:center; padding:20px;">در حال جستجو...</td></tr>`;
    }

    fetch('/publisher/search', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
        },
        body: JSON.stringify(searchData)
    })
        .then(r => {
            if (!r.ok) throw new Error('خطا در جستجو');
            return r.json();
        })
        .then(data => {
            updateTable(data);
            updateResultCount(data.length);
            showMessage(`${data.length} نتیجه یافت شد`, 'success');
        })
        .catch(err => {
            console.error('Error searching:', err);
            showMessage('خطا در جستجو: ' + err.message, 'error');
        });
}

function resetSearch() {
    const form = document.getElementById('searchForm');
    if (form) {
        const inputs = form.querySelectorAll('input, select');
        inputs.forEach(input => {
            input.value = '';
        });
        form.submit();
    }
}

// ============================
// توابع حذف
// ============================

function deletePublisher(id) {
    if (!id) {
        showMessage('شناسه معتبر نیست', 'error');
        return;
    }

    if (!confirm('آیا از حذف این ناشر اطمینان دارید؟')) {
        return;
    }

    fetch(`/publisher/deletePublisher/${id}`, {
        method: 'DELETE'
    })
        .then(r => {
            if (!r.ok) throw new Error('خطا در حذف');
            return r.json();
        })
        .then(data => {
            showMessage('ناشر با موفقیت حذف شد', 'success');
            setTimeout(() => {
                location.reload();
            }, 1500);
        })
        .catch(err => {
            console.error('Error deleting:', err);
            showMessage('خطا در حذف: ' + err.message, 'error');
        });
}

// ============================
// توابع کمکی
// ============================

function showMessage(message, type = 'success') {
    const container = document.querySelector('.container');
    if (!container) {
        console.error('Container element not found');
        return;
    }

    // حذف پیام‌های قبلی
    const existingAlerts = container.querySelectorAll('.alert');
    existingAlerts.forEach(el => el.remove());

    // ایجاد پیام جدید
    const alertDiv = document.createElement('div');
    alertDiv.className = `alert alert-${type}`;
    alertDiv.textContent = message;

    // اضافه کردن به صفحه
    const header = container.querySelector('h1');
    if (header) {
        header.parentNode.insertBefore(alertDiv, header.nextSibling);
    } else {
        container.insertBefore(alertDiv, container.firstChild);
    }

    // حذف خودکار بعد از 5 ثانیه
    setTimeout(() => {
        alertDiv.style.opacity = '0';
        alertDiv.style.transition = 'opacity 0.5s ease';
        setTimeout(() => {
            if (alertDiv.parentNode) {
                alertDiv.remove();
            }
        }, 500);
    }, 5000);
}

function updateTable(data) {
    const tbody = document.querySelector('table tbody');
    if (!tbody) return;

    if (!data || data.length === 0) {
        tbody.innerHTML = `
            <tr>
                <td colspan="11" class="empty-state">
                    <span class="icon">📭</span>
                    <div class="message">هیچ ناشری یافت نشد</div>
                </td>
            </tr>
        `;
        return;
    }

    let html = '';
    data.forEach(item => {
        html += `
            <tr>
                <td>${item.publisherId || ''}</td>
                <td>${item.publisherName || item.name || ''}</td>
                <td>${item.publisherTypeName || ''}</td>
                <td>${item.publisherCode || item.code || ''}</td>
                <td>${item.publisherCountry || item.country || ''}</td>
                <td>${item.publisherCity || item.city || ''}</td>
                <td>${item.publisherAddress || item.address || ''}</td>
                <td>${item.createdDate || ''}</td>
                <td>${item.createdTime || ''}</td>
                <td>${item.createdBy || ''}</td>
                <td>
                    <button onclick="openEditModal(this)" class="btn btn-warning btn-sm">
                        ✏️ ویرایش
                    </button>
                    <button onclick="deletePublisher(${item.publisherId})" class="btn btn-danger btn-sm">
                        🗑️ حذف
                    </button>
                </td>
            </tr>
        `;
    });
    tbody.innerHTML = html;
}

function updateResultCount(count) {
    const resultDiv = document.querySelector('.result-count');
    if (resultDiv) {
        resultDiv.innerHTML = `تعداد نتایج: <strong>${count}</strong>`;
    }
}

// ============================
// رویدادها
// ============================

document.addEventListener('DOMContentLoaded', function() {
    // بارگذاری انواع ناشر
    loadPublisherTypes();

    // جستجوی خودکار با تایپ
    const searchInput = document.getElementById('typeSearchInput');
    if (searchInput) {
        searchInput.addEventListener('input', function() {
            if (searchTimeout) {
                clearTimeout(searchTimeout);
                searchTimeout = null;
            }

            const term = this.value.trim();
            if (term.length >= 2 || term.length === 0) {
                searchTimeout = setTimeout(() => {
                    searchPublisherTypes();
                    searchTimeout = null;
                }, 400);
            }
        });
    }

    // بستن مودال با کلیک خارج
    window.onclick = function(event) {
        if (event.target.classList.contains('modal')) {
            event.target.style.display = 'none';
        }
    };

    // بستن مودال با کلید Escape
    document.addEventListener('keydown', function(event) {
        if (event.key === 'Escape') {
            document.querySelectorAll('.modal').forEach(modal => {
                if (modal.style.display === 'block') {
                    modal.style.display = 'none';
                }
            });
        }
    });

    // اضافه کردن event listener برای فرم (اگر می‌خواهید از AJAX استفاده کنید)
    const form = document.getElementById('publisherForm');
    if (form) {
        form.addEventListener('submit', submitPublisherForm);
    }

    console.log('🚀 صفحه مدیریت ناشران بارگذاری شد');
});