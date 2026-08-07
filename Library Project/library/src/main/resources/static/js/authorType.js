// authorType.js - مدیریت صفحه انواع نویسنده

// ============================
// متغیرهای سراسری
// ============================

let isEditMode = false;

// ============================
// توابع مدیریت فیلدهای شرطی
// ============================

function toggleConditionalFields() {
    const expertise = document.getElementById('authorExpertise')?.value || '';

    const artGroup = document.getElementById('artGroup');
    const engineeringGroup = document.getElementById('engineeringGroup');
    const humanitiesGroup = document.getElementById('humanitiesGroup');
    const historicalGroup = document.getElementById('historicalGroup');

    // مخفی کردن همه گروه‌ها
    if (artGroup) artGroup.style.display = 'none';
    if (engineeringGroup) engineeringGroup.style.display = 'none';
    if (humanitiesGroup) humanitiesGroup.style.display = 'none';
    if (historicalGroup) historicalGroup.style.display = 'none';


    if (expertise === 'ARTS' && artGroup) {
        artGroup.style.display = 'block';
    } else if (expertise === 'ENGINEERING' && engineeringGroup) {
        engineeringGroup.style.display = 'block';
    } else if (expertise === 'HUMANITIES' && humanitiesGroup) {
        humanitiesGroup.style.display = 'block';
    } else if (expertise === 'LITERATURE' && historicalGroup) {
        historicalGroup.style.display = 'block';
    }
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
    document.getElementById('formAction').value = 'save';
    isEditMode = false;

    // مخفی کردن همه گروه‌های شرطی
    document.querySelectorAll('.conditional').forEach(el => {
        el.style.display = 'none';
    });

    // تغییر action فرم برای ذخیره
    form.action = '/authorType/saveAuthorType';
    form.method = 'post';

    modal.style.display = 'block';

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
    document.getElementById('formAction').value = 'update';
    document.getElementById('authorExpertise').value = row.dataset.authorExpertise || '';
    document.getElementById('authorTypeRole').value = row.dataset.authorRole || '';
    document.getElementById('authorWritingStyle').value = row.dataset.writingStyle || '';
    document.getElementById('artExpertise').value = row.dataset.artExpertise || '';
    document.getElementById('engineeringExpertise').value = row.dataset.engineeringExpertise || '';
    document.getElementById('humanitiesExpertise').value = row.dataset.humanitiesExpertise || '';
    document.getElementById('historicalPeriodLevel').value = row.dataset.historical || '';

    isEditMode = true;

    // تغییر action فرم برای آپدیت
    form.action = '/authorType/updateAuthorType';
    form.method = 'post';

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
// ارسال فرم
// ============================

function submitAuthorTypeForm(event) {
    if (event) {
        event.preventDefault();
    }

    if (!validateAuthorTypeForm()) {
        return false;
    }

    // ارسال فرم به صورت معمولی
    document.getElementById('authorTypeForm').submit();
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

    // اضافه کردن event listener برای فرم
    const form = document.getElementById('authorTypeForm');
    if (form) {
        form.addEventListener('submit', submitAuthorTypeForm);
    }

    console.log('🚀 صفحه مدیریت انواع نویسنده بارگذاری شد');
});