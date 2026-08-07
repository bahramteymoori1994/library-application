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
        .catch(err => {
            console.error('Error loading publisher types:', err);
            showMessage('خطا در بارگذاری لیست انواع ناشر', 'error');
        });
}

function displayPublisherTypes(types) {
    const tbody = document.getElementById('publisherTypeTableBody');
    if (!tbody) return;

    let html = '';
    if (!types || types.length === 0) {
        html = `<tr><td colspan="3" class="empty-state">موردی یافت نشد</td></tr>`;
    } else {
        types.forEach(t => {
            const name = t.publisherTypeName || t.typeName || t.name || '';
            const id = t.publisherTypeId || t.id || '';
            const escapedName = name.replace(/'/g, "\\'");
            html += `
                <tr onclick="selectPublisherType('${id}','${escapedName}')">
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
    const submitBtn = document.getElementById('submitBtn');

    if (!modal || !title || !form) {
        console.error('عناصر مودال یافت نشدند');
        return;
    }

    title.textContent = 'ثبت ناشر جدید';
    submitBtn.textContent = 'ذخیره';
    submitBtn.className = 'btn btn-success';
    form.reset();
    document.getElementById('publisherId').value = '';
    document.getElementById('selectedTypeInfo').classList.remove('show');
    isEditMode = false;

    // تنظیم اکشن فرم برای ذخیره
    form.action = '/publisher/savePublisher';
    form.method = 'post';

    // حذف _method اگر وجود دارد
    const methodField = document.getElementById('_method');
    if (methodField) {
        methodField.remove();
    }

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

    const cells = row.querySelectorAll('td');
    if (cells.length < 1) {
        showMessage('داده‌های ردیف نامعتبر است', 'error');
        return;
    }

    const id = cells[0].textContent.trim();

    if (!id) {
        showMessage('شناسه ناشر معتبر نیست', 'error');
        return;
    }

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
            const submitBtn = document.getElementById('submitBtn');

            if (!modal || !title || !form) {
                console.error('عناصر مودال یافت نشدند');
                return;
            }

            title.textContent = 'ویرایش ناشر';
            submitBtn.textContent = 'به‌روزرسانی';
            submitBtn.className = 'btn btn-primary';

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
            const typeName = p.publisherTypeName || p.publisherType?.publisherTypeName ||
                p.publisherType?.name || '';

            document.getElementById('publisherTypeId').value = typeId;

            if (typeName) {
                document.getElementById('selectedTypeName').textContent = typeName;
                document.getElementById('selectedTypeInfo').classList.add('show');
            } else {
                document.getElementById('selectedTypeInfo').classList.remove('show');
            }

            isEditMode = true;

            // تنظیم اکشن فرم برای به‌روزرسانی
            form.action = '/publisher/updatePublisher';
            form.method = 'post';

            // اضافه کردن _method برای شبیه‌سازی PUT
            let methodField = document.getElementById('_method');
            if (!methodField) {
                methodField = document.createElement('input');
                methodField.type = 'hidden';
                methodField.id = '_method';
                methodField.name = '_method';
                form.appendChild(methodField);
            }
            methodField.value = 'PUT';

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
// ارسال فرم (ارسال معمولی)
// ============================

function submitPublisherForm(event) {
    if (event) {
        event.preventDefault();
    }

    if (!validatePublisherForm()) {
        return false;
    }

    // ارسال فرم به صورت معمولی
    document.getElementById('publisherForm').submit();
}

// ============================
// توابع جستجو
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

    // ارسال فرم جستجو
    form.submit();
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

    // اضافه کردن event listener برای فرم
    const form = document.getElementById('publisherForm');
    if (form) {
        form.addEventListener('submit', submitPublisherForm);
    }

    console.log('🚀 صفحه مدیریت ناشران بارگذاری شد');
});