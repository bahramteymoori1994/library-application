// publisherType.js - مدیریت صفحه انواع ناشران

// ============================
// متغیرهای سراسری
// ============================

let isEditMode = false;
let searchTimeout = null;

// ============================
// توابع مدیریت مودال
// ============================

function openCreateModal() {
    const modal = document.getElementById('publisherTypeModal');
    const title = document.getElementById('modalTitle');
    const submitBtn = document.getElementById('submitBtn');
    const form = document.getElementById('publisherTypeForm');

    if (!modal || !title || !submitBtn || !form) {
        console.error('عناصر مودال یافت نشدند');
        return;
    }

    title.textContent = 'ثبت نوع ناشر جدید';
    submitBtn.textContent = 'ذخیره';
    submitBtn.className = 'btn btn-success';
    form.reset();
    document.getElementById('publisherTypeId').value = '';
    isEditMode = false;

    // تغییر اکشن فرم برای ذخیره
    form.action = '/publisherType/savePublisherType';

    modal.style.display = 'block';

    setTimeout(() => {
        const typeName = document.getElementById('typeName');
        if (typeName) typeName.focus();
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

    const modal = document.getElementById('publisherTypeModal');
    const title = document.getElementById('modalTitle');
    const submitBtn = document.getElementById('submitBtn');
    const form = document.getElementById('publisherTypeForm');

    if (!modal || !title || !submitBtn || !form) {
        console.error('عناصر مودال یافت نشدند');
        return;
    }

    title.textContent = 'ویرایش نوع ناشر';
    submitBtn.textContent = 'به‌روزرسانی';
    submitBtn.className = 'btn btn-primary';

    // پر کردن فرم با داده‌های ردیف
    const id = row.dataset.id || '';
    const titleValue = row.dataset.typename || row.dataset.title || '';

    document.getElementById('publisherTypeId').value = id;
    document.getElementById('typeName').value = titleValue;

    isEditMode = true;

    // تغییر اکشن فرم برای آپدیت
    form.action = '/publisherType/updatePublisherType';

    modal.style.display = 'block';

    setTimeout(() => {
        const typeName = document.getElementById('typeName');
        if (typeName) typeName.focus();
    }, 100);
}

function closeModal() {
    const modal = document.getElementById('publisherTypeModal');
    if (modal) {
        modal.style.display = 'none';
    }
}

// ============================
// اعتبارسنجی فرم
// ============================

function validatePublisherTypeForm() {
    const typeName = document.getElementById('typeName');

    if (!typeName.value.trim()) {
        showMessage('لطفاً عنوان نوع ناشر را وارد کنید', 'error');
        typeName.focus();
        return false;
    }

    if (typeName.value.trim().length < 2) {
        showMessage('عنوان نوع ناشر باید حداقل ۲ کاراکتر باشد', 'error');
        typeName.focus();
        return false;
    }

    return true;
}

// ============================
// ارسال فرم با AJAX
// ============================

function submitPublisherTypeForm(event) {
    if (event) {
        event.preventDefault();
    }

    if (!validatePublisherTypeForm()) {
        return false;
    }

    const form = document.getElementById('publisherTypeForm');
    const formData = new FormData(form);
    const data = {};

    formData.forEach((value, key) => {
        data[key] = value;
    });

    const id = document.getElementById('publisherTypeId').value;
    const url = id ? '/publisherType/updatePublisherType' : '/publisherType/savePublisherType';
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
            showMessage('نوع ناشر با موفقیت ذخیره شد', 'success');
            setTimeout(() => {
                location.reload();
            }, 1500);
        })
        .catch(err => {
            console.error('Error saving publisher type:', err);
            showMessage('خطا در ذخیره اطلاعات: ' + err.message, 'error');
        });
}

// ============================
// توابع جستجو (اختیاری)
// ============================

function searchPublisherTypes(event) {
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
        tableBody.innerHTML = `<tr><td colspan="6" style="text-align:center; padding:20px;">در حال جستجو...</td></tr>`;
    }

    fetch('/publisherType/search', {
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

function deletePublisherType(id) {
    if (!id) {
        showMessage('شناسه معتبر نیست', 'error');
        return;
    }

    if (!confirm('آیا از حذف این نوع ناشر اطمینان دارید؟')) {
        return;
    }

    fetch(`/publisherType/deletePublisherType/${id}`, {
        method: 'DELETE'
    })
        .then(r => {
            if (!r.ok) throw new Error('خطا در حذف');
            return r.json();
        })
        .then(data => {
            showMessage('نوع ناشر با موفقیت حذف شد', 'success');
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
                <td colspan="6" class="empty-state">
                    <span class="icon">📭</span>
                    <div class="message">هیچ نوع ناشری یافت نشد</div>
                </td>
            </tr>
        `;
        return;
    }

    let html = '';
    data.forEach(item => {
        html += `
            <tr data-id="${item.publisherTypeId || ''}"
                data-typename="${item.typeName || ''}">
                <td>${item.publisherTypeId || ''}</td>
                <td>${item.typeName || ''}</td>
                <td>${item.createdDate || ''}</td>
                <td>${item.createdTime || ''}</td>
                <td>${item.createdBy || ''}</td>
                <td>
                    <button onclick="openEditModal(this)" class="btn btn-warning btn-sm">
                        ✏️ ویرایش
                    </button>
                    <button onclick="deletePublisherType(${item.publisherTypeId})" class="btn btn-danger btn-sm">
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
    // بستن مودال با کلیک خارج
    window.onclick = function(event) {
        if (event.target.classList.contains('modal')) {
            event.target.style.display = 'none';
        }
    };

    // بستن مودال با کلید Escape
    document.addEventListener('keydown', function(event) {
        if (event.key === 'Escape') {
            const modal = document.getElementById('publisherTypeModal');
            if (modal && modal.style.display === 'block') {
                modal.style.display = 'none';
            }
        }
    });

    // اضافه کردن event listener برای فرم (اگر می‌خواهید از AJAX استفاده کنید)
    const form = document.getElementById('publisherTypeForm');
    if (form) {
        form.addEventListener('submit', submitPublisherTypeForm);
    }

    console.log('🚀 صفحه مدیریت انواع ناشران بارگذاری شد');
});