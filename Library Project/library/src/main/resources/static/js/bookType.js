// bookType.js - مدیریت صفحه انواع کتاب

// ============================
// متغیرهای سراسری
// ============================

let isEditMode = false;
let searchTimeout = null;

// ============================
// توابع مدیریت فیلدهای شرطی
// ============================

function toggleConditionalFields() {
    const subject = document.getElementById('bookTypeSubject')?.value || '';

    const academicGroup = document.getElementById('academicLevelGroup');
    const historicalGroup = document.getElementById('historicalGroup');

    // سطح تحصیلی فقط برای علوم و مهندسی
    if (subject === 'SCIENCE' || subject === 'ENGINEERING') {
        if (academicGroup) {
            academicGroup.style.display = 'block';
            academicGroup.classList.add('visible');
        }
    } else {
        if (academicGroup) {
            academicGroup.style.display = 'none';
            academicGroup.classList.remove('visible');
            document.getElementById('bookAcademicLevel').value = '';
        }
    }

    // دوره تاریخی فقط برای ادبیات
    if (subject === 'LITERATURE') {
        if (historicalGroup) {
            historicalGroup.style.display = 'block';
            historicalGroup.classList.add('visible');
        }
    } else {
        if (historicalGroup) {
            historicalGroup.style.display = 'none';
            historicalGroup.classList.remove('visible');
            document.getElementById('historicalPeriodLevel').value = '';
        }
    }
}

// ============================
// توابع مدیریت مودال
// ============================

function openCreateModal() {
    const modal = document.getElementById('bookTypeModal');
    const title = document.getElementById('modalTitle');
    const submitBtn = document.getElementById('submitBtn');
    const form = document.getElementById('bookTypeForm');

    if (!modal || !title || !submitBtn || !form) {
        console.error('عناصر مودال یافت نشدند');
        return;
    }

    title.textContent = 'ثبت نوع کتاب جدید';
    submitBtn.textContent = 'ذخیره';
    submitBtn.className = 'btn btn-success';
    form.reset();
    document.getElementById('bookTypeId').value = '';
    isEditMode = false;

    // تغییر اکشن فرم برای ذخیره
    form.action = '/bookType/saveBookType';

    modal.style.display = 'block';
    toggleConditionalFields();

    setTimeout(() => {
        const subject = document.getElementById('bookTypeSubject');
        if (subject) subject.focus();
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

    const modal = document.getElementById('bookTypeModal');
    const title = document.getElementById('modalTitle');
    const submitBtn = document.getElementById('submitBtn');
    const form = document.getElementById('bookTypeForm');

    if (!modal || !title || !submitBtn || !form) {
        console.error('عناصر مودال یافت نشدند');
        return;
    }

    title.textContent = 'ویرایش نوع کتاب';
    submitBtn.textContent = 'به‌روزرسانی';
    submitBtn.className = 'btn btn-primary';

    // پر کردن فرم با داده‌های ردیف
    document.getElementById('bookTypeId').value = row.dataset.id || '';
    document.getElementById('bookTypeSubject').value = row.dataset.subject || '';
    document.getElementById('bookTypeAgeGroup').value = row.dataset.agegroup || '';
    document.getElementById('bookTypeLanguage').value = row.dataset.language || '';
    document.getElementById('bookTypePublicationStatus').value = row.dataset.pubstatus || '';
    document.getElementById('bookAcademicLevel').value = row.dataset.academiclevel || '';
    document.getElementById('historicalPeriodLevel').value = row.dataset.historical || '';

    isEditMode = true;

    // تغییر اکشن فرم برای آپدیت
    form.action = '/bookType/updateBookType';

    modal.style.display = 'block';

    // با تاخیر کوتاه فیلدهای شرطی را نمایش بده
    setTimeout(toggleConditionalFields, 50);

    setTimeout(() => {
        const subject = document.getElementById('bookTypeSubject');
        if (subject) subject.focus();
    }, 150);
}

function closeModal() {
    const modal = document.getElementById('bookTypeModal');
    if (modal) {
        modal.style.display = 'none';
    }
}

// ============================
// اعتبارسنجی فرم
// ============================

function validateBookTypeForm() {
    const subject = document.getElementById('bookTypeSubject');
    const ageGroup = document.getElementById('bookTypeAgeGroup');
    const language = document.getElementById('bookTypeLanguage');
    const pubStatus = document.getElementById('bookTypePublicationStatus');

    if (!subject.value) {
        showMessage('لطفاً موضوع کتاب را انتخاب کنید', 'error');
        subject.focus();
        return false;
    }

    if (!ageGroup.value) {
        showMessage('لطفاً گروه سنی را انتخاب کنید', 'error');
        ageGroup.focus();
        return false;
    }

    if (!language.value) {
        showMessage('لطفاً زبان کتاب را انتخاب کنید', 'error');
        language.focus();
        return false;
    }

    if (!pubStatus.value) {
        showMessage('لطفاً وضعیت انتشار را انتخاب کنید', 'error');
        pubStatus.focus();
        return false;
    }

    // اعتبارسنجی فیلدهای شرطی
    const selectedSubject = subject.value;

    if (selectedSubject === 'SCIENCE' || selectedSubject === 'ENGINEERING') {
        const academicLevel = document.getElementById('bookAcademicLevel');
        if (!academicLevel.value) {
            showMessage('لطفاً سطح تحصیلی را انتخاب کنید', 'error');
            academicLevel.focus();
            return false;
        }
    }

    if (selectedSubject === 'LITERATURE') {
        const historicalPeriod = document.getElementById('historicalPeriodLevel');
        if (!historicalPeriod.value) {
            showMessage('لطفاً دوره تاریخی را انتخاب کنید', 'error');
            historicalPeriod.focus();
            return false;
        }
    }

    return true;
}

// ============================
// ارسال فرم با AJAX
// ============================

function submitBookTypeForm(event) {
    if (event) {
        event.preventDefault();
    }

    if (!validateBookTypeForm()) {
        return false;
    }

    const form = document.getElementById('bookTypeForm');
    const formData = new FormData(form);
    const data = {};

    formData.forEach((value, key) => {
        data[key] = value;
    });

    const id = document.getElementById('bookTypeId').value;
    const url = id ? '/bookType/updateBookType' : '/bookType/saveBookType';
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
            showMessage('نوع کتاب با موفقیت ذخیره شد', 'success');
            setTimeout(() => {
                location.reload();
            }, 1500);
        })
        .catch(err => {
            console.error('Error saving book type:', err);
            showMessage('خطا در ذخیره اطلاعات: ' + err.message, 'error');
        });
}

// ============================
// توابع جستجو (اختیاری)
// ============================

function searchBookTypes(event) {
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

    fetch('/bookType/search', {
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

function deleteBookType(id) {
    if (!id) {
        showMessage('شناسه معتبر نیست', 'error');
        return;
    }

    if (!confirm('آیا از حذف این نوع کتاب اطمینان دارید؟')) {
        return;
    }

    fetch(`/bookType/deleteBookType/${id}`, {
        method: 'DELETE'
    })
        .then(r => {
            if (!r.ok) throw new Error('خطا در حذف');
            return r.json();
        })
        .then(data => {
            showMessage('نوع کتاب با موفقیت حذف شد', 'success');
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
                    <div class="message">هیچ نوع کتابی یافت نشد</div>
                </td>
            </tr>
        `;
        return;
    }

    let html = '';
    data.forEach(item => {
        html += `
            <tr data-id="${item.bookTypeId || ''}"
                data-subject="${item.bookTypeSubject || ''}"
                data-agegroup="${item.bookTypeAgeGroup || ''}"
                data-language="${item.bookTypeLanguage || ''}"
                data-pubstatus="${item.bookTypePublicationStatus || ''}"
                data-academiclevel="${item.bookAcademicLevel || ''}"
                data-historical="${item.historicalPeriodLevel || ''}">
                <td>${item.bookTypeId || ''}</td>
                <td>${item.bookTypeSubject || ''}</td>
                <td>${item.bookTypeAgeGroup || ''}</td>
                <td>${item.bookTypeLanguage || ''}</td>
                <td>${item.bookTypePublicationStatus || ''}</td>
                <td>${item.bookAcademicLevel || '-'}</td>
                <td>${item.historicalPeriodLevel || '-'}</td>
                <td>${item.createdDate || ''}</td>
                <td>${item.createdTime || ''}</td>
                <td>${item.createdBy || ''}</td>
                <td>
                    <button onclick="openEditModal(this)" class="btn btn-warning btn-sm">
                        ✏️ ویرایش
                    </button>
                    <button onclick="deleteBookType(${item.bookTypeId})" class="btn btn-danger btn-sm">
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
    // رویداد تغییر موضوع برای نمایش فیلدهای شرطی
    const subjectSelect = document.getElementById('bookTypeSubject');
    if (subjectSelect) {
        subjectSelect.addEventListener('change', toggleConditionalFields);
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
            const modal = document.getElementById('bookTypeModal');
            if (modal && modal.style.display === 'block') {
                modal.style.display = 'none';
            }
        }
    });

    // اضافه کردن event listener برای فرم (اگر می‌خواهید از AJAX استفاده کنید)
    const form = document.getElementById('bookTypeForm');
    if (form) {
        form.addEventListener('submit', submitBookTypeForm);
    }

    console.log('🚀 صفحه مدیریت انواع کتاب بارگذاری شد');
});