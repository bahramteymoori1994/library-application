// person.js - مدیریت صفحه اشخاص

// ============================
// مدیریت مودال
// ============================

function openCreateModal() {
    const modal = document.getElementById('personModal');
    const title = document.getElementById('modalTitle');
    const submitBtn = document.getElementById('submitBtn');
    const form = document.getElementById('personForm');

    title.textContent = 'ثبت شخص جدید';
    submitBtn.textContent = 'ذخیره';
    form.reset();
    document.getElementById('personId').value = '';
    form.action = '/person/savePerson';
    modal.style.display = 'block';

    // فوکوس روی اولین فیلد
    setTimeout(() => {
        document.getElementById('firstName').focus();
    }, 100);
}

function openEditModal(btn) {
    const row = btn.closest('tr');
    const modal = document.getElementById('personModal');
    const title = document.getElementById('modalTitle');
    const submitBtn = document.getElementById('submitBtn');
    const form = document.getElementById('personForm');

    title.textContent = 'ویرایش شخص';
    submitBtn.textContent = 'به‌روزرسانی';

    // پر کردن فرم با داده‌های ردیف
    document.getElementById('personId').value = row.dataset.id || '';
    document.getElementById('firstName').value = row.dataset.firstname || '';
    document.getElementById('lastName').value = row.dataset.lastname || '';
    document.getElementById('nationalCode').value = row.dataset.nationalcode || '';
    document.getElementById('fatherName').value = row.dataset.fathername || '';
    document.getElementById('birthDate').value = row.dataset.birthdate || '';
    document.getElementById('gender').value = row.dataset.gender || 'MALE';
    document.getElementById('phoneNumber').value = row.dataset.phonenumber || '';

    // تغییر اکشن فرم برای آپدیت
    form.action = '/person/updatePerson';
    modal.style.display = 'block';
}

function closeModal() {
    document.getElementById('personModal').style.display = 'none';
}

// بستن مودال با کلیک خارج از آن
window.onclick = function(event) {
    const modal = document.getElementById('personModal');
    if (event.target === modal) {
        closeModal();
    }
};

// بستن مودال با دکمه ESC
document.addEventListener('keydown', function(event) {
    if (event.key === 'Escape') {
        closeModal();
    }
});

// ============================
// مدیریت جستجو
// ============================

function resetSearch() {
    // پاک کردن همه فیلدهای جستجو
    const form = document.querySelector('.search-panel form');
    if (form) {
        const inputs = form.querySelectorAll('input, select');
        inputs.forEach(input => {
            if (input.type === 'date') {
                input.value = '';
            } else if (input.type === 'text' || input.type === 'search') {
                input.value = '';
            } else if (input.tagName === 'SELECT') {
                input.value = '';
            }
        });

        // ارسال فرم با مقادیر خالی
        form.submit();
    }
}

// ============================
// اعتبارسنجی فرم
// ============================

document.addEventListener('DOMContentLoaded', function() {
    const form = document.getElementById('personForm');
    if (form) {
        form.addEventListener('submit', function(event) {
            const nationalCode = document.getElementById('nationalCode');
            const phoneNumber = document.getElementById('phoneNumber');

            // اعتبارسنجی کد ملی (10 رقم)
            if (nationalCode && nationalCode.value) {
                const code = nationalCode.value.replace(/\s/g, '');
                if (!/^\d{10}$/.test(code)) {
                    event.preventDefault();
                    alert('کد ملی باید 10 رقم باشد');
                    nationalCode.focus();
                    return false;
                }
            }

            // اعتبارسنجی شماره تلفن (11 رقم)
            if (phoneNumber && phoneNumber.value) {
                const phone = phoneNumber.value.replace(/\s/g, '');
                if (!/^\d{11}$/.test(phone)) {
                    event.preventDefault();
                    alert('شماره تلفن باید 11 رقم باشد');
                    phoneNumber.focus();
                    return false;
                }
            }
        });
    }
});

// ============================
// جستجوی پیشرفته با AJAX (اختیاری)
// ============================

function searchPeopleAjax(event) {
    if (event) {
        event.preventDefault();
    }

    const form = document.getElementById('searchForm');
    if (!form) return;

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
        tableBody.innerHTML = '<tr><td colspan="12" style="text-align:center; padding:20px;">در حال جستجو...</td></tr>';
    }

    fetch('/person/findAllPeopleSpecification', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
        },
        body: JSON.stringify(searchData)
    })
        .then(response => {
            if (!response.ok) {
                throw new Error('خطا در ارتباط با سرور');
            }
            return response.json();
        })
        .then(data => {
            updateTable(data);
            updateResultCount(data.length);
        })
        .catch(error => {
            console.error('Error:', error);
            if (tableBody) {
                tableBody.innerHTML = `<tr><td colspan="12" style="text-align:center; color:red; padding:20px;">خطا در بارگذاری داده‌ها: ${error.message}</td></tr>`;
            }
        });
}

function updateTable(persons) {
    const tbody = document.querySelector('table tbody');
    if (!tbody) return;

    if (!persons || persons.length === 0) {
        tbody.innerHTML = `
            <tr>
                <td colspan="12" style="text-align:center; color:#888; padding:20px;">
                    🕵️ هیچ شخصی یافت نشد
                </td>
            </tr>
        `;
        return;
    }

    let html = '';
    persons.forEach(p => {
        const genderText = p.gender === 'MALE' ? 'مرد' : 'زن';
        const badgeClass = p.gender === 'MALE' ? 'badge-male' : 'badge-female';

        html += `
            <tr data-id="${p.personId || ''}"
                data-firstname="${p.firstName || ''}"
                data-lastname="${p.lastName || ''}"
                data-nationalcode="${p.nationalCode || ''}"
                data-fathername="${p.fatherName || ''}"
                data-birthdate="${p.birthDate || ''}"
                data-gender="${p.gender || ''}"
                data-phonenumber="${p.phoneNumber || ''}">
                <td>${p.personId || ''}</td>
                <td>${p.firstName || ''}</td>
                <td>${p.lastName || ''}</td>
                <td>${p.nationalCode || ''}</td>
                <td>${p.fatherName || ''}</td>
                <td>${p.birthDate || ''}</td>
                <td>
                    <span class="badge ${badgeClass}">${genderText}</span>
                </td>
                <td>${p.phoneNumber || ''}</td>
                <td>${p.createdDate || ''}</td>
                <td>${p.createdTime || ''}</td>
                <td>${p.createdBy || ''}</td>
                <td>
                    <button onclick="openEditModal(this)" 
                            class="btn btn-warning btn-sm">
                        ✏️ ویرایش
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
// ابزارهای کمکی
// ============================

// فرمت کردن تاریخ برای نمایش
function formatDate(dateString) {
    if (!dateString) return '';
    try {
        const date = new Date(dateString);
        return date.toLocaleDateString('fa-IR');
    } catch (e) {
        return dateString;
    }
}

// نمایش پیام موفقیت/خطا
function showMessage(message, type = 'success') {
    const container = document.querySelector('.container');
    if (!container) return;

    const alertDiv = document.createElement('div');
    alertDiv.className = `alert alert-${type}`;
    alertDiv.textContent = message;

    // حذف پیام‌های قبلی
    const existingAlerts = container.querySelectorAll('.alert');
    existingAlerts.forEach(el => el.remove());

    // اضافه کردن پیام جدید
    container.insertBefore(alertDiv, container.firstChild);

    // حذف خودکار پیام بعد از 5 ثانیه
    setTimeout(() => {
        alertDiv.remove();
    }, 5000);
}

// ============================
// تابع برای جستجوی خودکار با Enter
// ============================

document.addEventListener('DOMContentLoaded', function() {
    const searchInputs = document.querySelectorAll('.search-group input, .search-group select');
    searchInputs.forEach(input => {
        input.addEventListener('keypress', function(event) {
            if (event.key === 'Enter') {
                event.preventDefault();
                const form = this.closest('form');
                if (form) {
                    form.submit();
                }
            }
        });
    });
});