// authorType.js - مدیریت صفحه انواع نویسنده

// ============================
// متغیرهای سراسری
// ============================

let isEditMode = false;
let searchTimeout = null;

// ============================
// توابع مدیریت فیلدهای شرطی
// ============================

function toggleConditionalFields() {
    const expertise = document.getElementById('authorExpertise')?.value || '';

    const artGroup = document.getElementById('artGroup');
    const engineeringGroup = document.getElementById('engineeringGroup');
    const humanitiesGroup = document.getElementById('humanitiesGroup');
    const historicalGroup = document.getElementById('historicalGroup');

    // نمایش فیلدها بر اساس تخصص
    if (artGroup) {
        artGroup.style.display = (expertise === 'ARTS') ? 'block' : 'none';
        if (expertise === 'ARTS') artGroup.classList.add('visible');
        else artGroup.classList.remove('visible');
    }

    if (engineeringGroup) {
        engineeringGroup.style.display = (expertise === 'ENGINEERING') ? 'block' : 'none';
        if (expertise === 'ENGINEERING') engineeringGroup.classList.add('visible');
        else engineeringGroup.classList.remove('visible');
    }

    if (humanitiesGroup) {
        humanitiesGroup.style.display = (expertise === 'HUMANITIES') ? 'block' : 'none';
        if (expertise === 'HUMANITIES') humanitiesGroup.classList.add('visible');
        else humanitiesGroup.classList.remove('visible');
    }

    if (historicalGroup) {
        historicalGroup.style.display = (expertise === 'LITERATURE') ? 'block' : 'none';
        if (expertise === 'LITERATURE') historicalGroup.classList.add('visible');
        else historicalGroup.classList.remove('visible');
    }

    // پاک کردن مقادیر فیلدهای غیرفعال
    const artExpertise = document.getElementById('artExpertise');
    const engineeringExpertise = document.getElementById('engineeringExpertise');
    const humanitiesExpertise = document.getElementById('humanitiesExpertise');
    const historicalPeriod = document.getElementById('historicalPeriodLevel');

    if (expertise !== 'ARTS' && artExpertise) artExpertise.value = '';
    if (expertise !== 'ENGINEERING' && engineeringExpertise) engineeringExpertise.value = '';
    if (expertise !== 'HUMANITIES' && humanitiesExpertise) humanitiesExpertise.value = '';
    if (expertise !== 'LITERATURE' && historicalPeriod) historicalPeriod.value = '';
}

// ============================
// توابع مدیریت مودال
// ============================

function openCreateModal() {
    const modal = document.getElementById('authorTypeModal');
    const title = document.getElementById('modalTitle');
    const submitBtn = document.getElementById('submitBtn');
    const form = document.getElementById('authorTypeForm');

    if (!modal || !title || !submitBtn || !form) {
        console.error('عناصر مودال یافت نشدند');
        return;
    }

    title.textContent = 'ثبت نوع نویسنده جدید';
    submitBtn.textContent = 'ذخیره';
    submitBtn.className = 'btn btn-success';
    form.reset();
    document.getElementById('authorTypeId').value = '';
    isEditMode = false;

    // تغییر اکشن فرم برای ذخیره
    form.action = '/authorType/saveAuthorType';

    modal.style.display = 'block';
    toggleConditionalFields();

    setTimeout(() => {
        const expertise = document.getElementById('authorExpertise');
        if (expertise) expertise.focus();
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

    const modal = document.getElementById('authorTypeModal');
    const title = document.getElementById('modalTitle');
    const submitBtn = document.getElementById('submitBtn');
    const form = document.getElementById('authorTypeForm');

    if (!modal || !title || !submitBtn || !form) {
        console.error('عناصر مودال یافت نشدند');
        return;
    }

    title.textContent = 'ویرایش نوع نویسنده';
    submitBtn.textContent = 'به‌روزرسانی';
    submitBtn.className = 'btn btn-primary';

    // پر کردن فرم با داده‌های ردیف
    document.getElementById('authorTypeId').value = row.dataset.id || '';
    document.getElementById('authorExpertise').value = row.dataset.authorExpertise || '';
    document.getElementById('authorTypeRole').value = row.dataset.authorRole || '';
    document.getElementById('authorWritingStyle').value = row.dataset.writingStyle || '';
    document.getElementById('artExpertise').value = row.dataset.artExpertise || '';
    document.getElementById('engineeringExpertise').value = row.dataset.engineeringExpertise || '';
    document.getElementById('humanitiesExpertise').value = row.dataset.humanitiesExpertise || '';
    document.getElementById('historicalPeriodLevel').value = row.dataset.historical || '';

    isEditMode = true;

    // تغییر اکشن فرم برای آپدیت
    form.action = '/authorType/updateAuthorType';

    modal.style.display = 'block';

    // با تاخیر کوتاه فیلدهای شرطی را نمایش بده
    setTimeout(toggleConditionalFields, 50);

    setTimeout(() => {
        const expertise = document.getElementById('authorExpertise');
        if (expertise) expertise.focus();
    }, 150);
}

function closeModal() {
    const modal = document.getElementById('authorTypeModal');
    if (modal) {
        modal.style.display = 'none';
    }
}

// ============================
// اعتبارسنجی فرم
// ============================

function validateAuthorTypeForm() {
    const expertise = document.getElementById('authorExpertise');
    const role = document.getElementById('authorTypeRole');

    if (!expertise.value) {
        showMessage('لطفاً تخصص نویسنده را انتخاب کنید', 'error');
        expertise.focus();
        return false;
    }

    if (!role.value) {
        showMessage('لطفاً نقش نویسنده را انتخاب کنید', 'error');
        role.focus();
        return false;
    }

    // اعتبارسنجی فیلدهای شرطی
    const selectedExpertise = expertise.value;

    if (selectedExpertise === 'ARTS') {
        const artExpertise = document.getElementById('artExpertise');
        if (!artExpertise.value) {
            showMessage('لطفاً تخصص هنری را انتخاب کنید', 'error');
            artExpertise.focus();
            return false;
        }
    }

    if (selectedExpertise === 'ENGINEERING') {
        const engineeringExpertise = document.getElementById('engineeringExpertise');
        if (!engineeringExpertise.value) {
            showMessage('لطفاً تخصص مهندسی را انتخاب کنید', 'error');
            engineeringExpertise.focus();
            return false;
        }
    }

    if (selectedExpertise === 'HUMANITIES') {
        const humanitiesExpertise = document.getElementById('humanitiesExpertise');
        if (!humanitiesExpertise.value) {
            showMessage('لطفاً تخصص علوم انسانی را انتخاب کنید', 'error');
            humanitiesExpertise.focus();
            return false;
        }
    }

    return true;
}

// ============================
// ارسال فرم با AJAX
// ============================

function submitAuthorTypeForm(event) {
    if (event) {
        event.preventDefault();
    }

    if (!validateAuthorTypeForm()) {
        return false;
    }

    const form = document.getElementById('authorTypeForm');
    const formData = new FormData(form);
    const data = {};

    formData.forEach((value, key) => {
        data[key] = value;
    });

    const id = document.getElementById('authorTypeId').value;
    const url = id ? '/authorType/updateAuthorType' : '/authorType/saveAuthorType';
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
            showMessage('نوع نویسنده با موفقیت ذخیره شد', 'success');
            setTimeout(() => {
                location.reload();
            }, 1500);
        })
        .catch(err => {
            console.error('Error saving author type:', err);
            showMessage('خطا در ذخیره اطلاعات: ' + err.message, 'error');
        });
}

// ============================
// توابع جستجو (اختیاری)
// ============================

function searchAuthorTypes(event) {
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
        tableBody.innerHTML = `<tr><td colspan="12" style="text-align:center; padding:20px;">در حال جستجو...</td></tr>`;
    }

    fetch('/authorType/search', {
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

function deleteAuthorType(id) {
    if (!id) {
        showMessage('شناسه معتبر نیست', 'error');
        return;
    }

    if (!confirm('آیا از حذف این نوع نویسنده اطمینان دارید؟')) {
        return;
    }

    fetch(`/authorType/deleteAuthorType/${id}`, {
        method: 'DELETE'
    })
        .then(r => {
            if (!r.ok) throw new Error('خطا در حذف');
            return r.json();
        })
        .then(data => {
            showMessage('نوع نویسنده با موفقیت حذف شد', 'success');
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
                <td colspan="12" class="empty-state">
                    <span class="icon">📭</span>
                    <div class="message">هیچ نوع نویسنده‌ای یافت نشد</div>
                </td>
            </tr>
        `;
        return;
    }

    let html = '';
    data.forEach(item => {
        html += `
            <tr data-id="${item.authorTypeId || ''}"
                data-author-expertise="${item.authorExpertise || ''}"
                data-author-role="${item.authorTypeRole || ''}"
                data-writing-style="${item.authorWritingStyle || ''}"
                data-art-expertise="${item.artExpertise || ''}"
                data-engineering-expertise="${item.engineeringExpertise || ''}"
                data-humanities-expertise="${item.humanitiesExpertise || ''}"
                data-historical="${item.historicalPeriodLevel || ''}">
                <td>${item.authorTypeId || ''}</td>
                <td>${item.authorExpertise || ''}</td>
                <td>${item.authorTypeRole || ''}</td>
                <td>${item.authorWritingStyle || ''}</td>
                <td>${item.artExpertise || '-'}</td>
                <td>${item.engineeringExpertise || '-'}</td>
                <td>${item.humanitiesExpertise || '-'}</td>
                <td>${item.historicalPeriodLevel || '-'}</td>
                <td>${item.createdDate || ''}</td>
                <td>${item.createdTime || ''}</td>
                <td>${item.createdBy || ''}</td>
                <td>
                    <button onclick="openEditModal(this)" class="btn btn-warning btn-sm">
                        ✏️ ویرایش
                    </button>
                    <button onclick="deleteAuthorType(${item.authorTypeId})" class="btn btn-danger btn-sm">
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
    // رویداد تغییر تخصص برای نمایش فیلدهای شرطی
    const expertiseSelect = document.getElementById('authorExpertise');
    if (expertiseSelect) {
        expertiseSelect.addEventListener('change', toggleConditionalFields);
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
            const modal = document.getElementById('authorTypeModal');
            if (modal && modal.style.display === 'block') {
                modal.style.display = 'none';
            }
        }
    });

    // اضافه کردن event listener برای فرم (اگر می‌خواهید از AJAX استفاده کنید)
    const form = document.getElementById('authorTypeForm');
    if (form) {
        form.addEventListener('submit', submitAuthorTypeForm);
    }

    console.log('🚀 صفحه مدیریت انواع نویسنده بارگذاری شد');
});