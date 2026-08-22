// role.js - مدیریت صفحه نقش‌ها
// ============================
// متغیرهای سراسری
// ============================
let isEditMode = false;

// ============================
// توابع مدیریت مودال
// ============================
function openCreateModal() {
    const modal = document.getElementById('roleModal');
    const title = document.getElementById('modalTitle');
    const submitBtn = document.getElementById('submitBtn');
    const form = document.getElementById('roleForm');

    if (!modal || !title || !submitBtn || !form) {
        console.error('عناصر مودال یافت نشدند');
        return;
    }

    title.textContent = 'ثبت نقش جدید';
    submitBtn.textContent = 'ذخیره';
    submitBtn.className = 'btn btn-success';

    form.reset();
    document.getElementById('roleId').value = '';
    isEditMode = false;

    modal.style.display = 'block';

    setTimeout(() => {
        const englishTitle = document.getElementById('englishRoleTitle');
        if (englishTitle) englishTitle.focus();
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

    const modal = document.getElementById('roleModal');
    const title = document.getElementById('modalTitle');
    const submitBtn = document.getElementById('submitBtn');
    const form = document.getElementById('roleForm');

    if (!modal || !title || !submitBtn || !form) {
        console.error('عناصر مودال یافت نشدند');
        return;
    }

    title.textContent = 'ویرایش نقش';
    submitBtn.textContent = 'به‌روزرسانی';
    submitBtn.className = 'btn btn-primary';

    document.getElementById('roleId').value = row.dataset.id || '';
    document.getElementById('englishRoleTitle').value = row.dataset.englishtitle || '';
    document.getElementById('farsiRoleTitle').value = row.dataset.farsititle || '';

    isEditMode = true;

    modal.style.display = 'block';

    setTimeout(() => {
        const englishTitle = document.getElementById('englishRoleTitle');
        if (englishTitle) englishTitle.focus();
    }, 100);
}

function closeModal() {
    const modal = document.getElementById('roleModal');
    if (modal) {
        modal.style.display = 'none';
    }
}

// ============================
// اعتبارسنجی فرم
// ============================
function validateRoleForm() {
    const englishTitle = document.getElementById('englishRoleTitle');
    const farsiTitle = document.getElementById('farsiRoleTitle');

    if (!englishTitle.value.trim()) {
        showMessage('لطفاً عنوان انگلیسی نقش را وارد کنید', 'error');
        englishTitle.focus();
        return false;
    }

    if (!farsiTitle.value.trim()) {
        showMessage('لطفاً عنوان فارسی نقش را وارد کنید', 'error');
        farsiTitle.focus();
        return false;
    }

    return true;
}

// ============================
// ارسال فرم
// ============================
function submitRoleForm(event) {
    if (event) {
        event.preventDefault();
    }

    if (!validateRoleForm()) {
        return false;
    }

    const roleId = document.getElementById('roleId').value;
    const isUpdate = !!roleId;

    showMessage('در حال ذخیره اطلاعات...', 'info');

    if (isUpdate) {
        // ===== ویرایش (PUT + JSON) =====
        const roleData = {
            roleId: roleId,
            englishRoleTitle: document.getElementById('englishRoleTitle').value.trim(),
            farsiRoleTitle: document.getElementById('farsiRoleTitle').value.trim()
        };

        fetch('/role/updateRole', {
            method: 'PUT',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(roleData)
        })
            .then(r => {
                if (!r.ok) throw new Error('خطا در به‌روزرسانی');
                return r.json();
            })
            .then(data => {
                closeModal();
                showMessage('نقش با موفقیت به‌روزرسانی شد', 'success');
                setTimeout(() => location.reload(), 1200);
            })
            .catch(err => {
                console.error(err);
                showMessage('خطا در به‌روزرسانی: ' + err.message, 'error');
            });

    } else {
        // ===== ثبت جدید (POST + FormData) =====
        // چون کنترلر با @ModelAttribute کار می‌کند
        const form = document.getElementById('roleForm');
        const formData = new FormData(form);

        fetch('/role/saveRole', {
            method: 'POST',
            body: formData          // بدون Content-Type تا مرورگر خودش تنظیم کند
        })
            .then(r => {
                if (!r.ok) throw new Error('خطا در ثبت');
                // کنترلر redirect برمی‌گرداند → نیازی به json نیست
                return r.text();
            })
            .then(() => {
                closeModal();
                showMessage('نقش با موفقیت ثبت شد', 'success');
                setTimeout(() => location.reload(), 1200);
            })
            .catch(err => {
                console.error(err);
                showMessage('خطا در ثبت: ' + err.message, 'error');
            });
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

    const alertDiv = document.createElement('div');
    alertDiv.className = `alert alert-${type}`;
    alertDiv.textContent = message;

    const header = container.querySelector('h1');
    if (header) {
        header.parentNode.insertBefore(alertDiv, header.nextSibling);
    } else {
        container.insertBefore(alertDiv, container.firstChild);
    }

    setTimeout(() => {
        alertDiv.style.opacity = '0';
        alertDiv.style.transition = 'opacity 0.5s ease';
        setTimeout(() => {
            if (alertDiv.parentNode) alertDiv.remove();
        }, 500);
    }, 5000);
}

// ============================
// رویدادها
// ============================
document.addEventListener('DOMContentLoaded', function () {
    // بستن مودال با کلیک خارج
    window.onclick = function (event) {
        if (event.target.classList.contains('modal')) {
            event.target.style.display = 'none';
        }
    };

    // بستن مودال با کلید Escape
    document.addEventListener('keydown', function (event) {
        if (event.key === 'Escape') {
            const modal = document.getElementById('roleModal');
            if (modal && modal.style.display === 'block') {
                modal.style.display = 'none';
            }
        }
    });

    // اتصال فرم به تابع submit
    const form = document.getElementById('roleForm');
    if (form) {
        form.addEventListener('submit', submitRoleForm);
    }

    console.log('🚀 صفحه مدیریت نقش‌ها بارگذاری شد');
});

// ============================
// تابع حذف نقش (اختیاری)
// ============================
function deleteRole(roleId) {
    if (!roleId) {
        showMessage('شناسه نقش معتبر نیست', 'error');
        return;
    }

    if (!confirm('آیا از حذف این نقش اطمینان دارید؟')) {
        return;
    }

    fetch(`/role/deleteRole/${roleId}`, {
        method: 'DELETE'
    })
        .then(r => {
            if (!r.ok) throw new Error('خطا در حذف نقش');
            return r.json();
        })
        .then(() => {
            showMessage('نقش با موفقیت حذف شد', 'success');
            setTimeout(() => location.reload(), 1200);
        })
        .catch(err => {
            console.error(err);
            showMessage('خطا در حذف نقش: ' + err.message, 'error');
        });
}