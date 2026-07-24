// author.js - مدیریت صفحه نویسندگان

let allPersons = [];
let allAuthorTypes = [];
let personSearchTimeout = null;
let authorTypeSearchTimeout = null;

// =============================================
// ================ اشخاص ======================
// =============================================

function loadPersons() {
    fetch('/author/findAllPeople')
        .then(r => r.json())
        .then(data => {
            allPersons = data;
            displayPersons(data);
        })
        .catch(err => showMessage('خطا در بارگذاری اشخاص', 'error'));
}

function displayPersons(persons) {
    const tbody = document.getElementById('personTableBody');
    if (!tbody) return;

    let html = '';
    if (!persons || persons.length === 0) {
        html = `<tr><td colspan="5" class="empty-state">موردی یافت نشد</td></tr>`;
    } else {
        persons.forEach(p => {
            const firstName = p.firstName || '';
            const lastName = p.lastName || '';
            const nationalCode = p.nationalCode || '';
            html += `
                <tr onclick="selectPerson('${p.personId}','${firstName.replace(/'/g, "\\'")}','${lastName.replace(/'/g, "\\'")}','${nationalCode}')">
                    <td>${p.personId}</td>
                    <td>${firstName}</td>
                    <td>${lastName}</td>
                    <td>${nationalCode}</td>
                    <td><button class="btn btn-success btn-sm">انتخاب</button></td>
                </tr>
            `;
        });
    }
    tbody.innerHTML = html;
}

function selectPerson(id, firstName, lastName, nationalCode) {
    document.getElementById('personId').value = id;
    const infoDiv = document.getElementById('selectedPersonInfo');
    infoDiv.classList.add('show');
    document.getElementById('selectedFirstName').textContent = firstName;
    document.getElementById('selectedLastName').textContent = lastName;
    document.getElementById('selectedNationalCode').textContent = nationalCode;
    closePersonSearch();
    showMessage('شخص با موفقیت انتخاب شد', 'success');
}

function openPersonSearch() {
    const modal = document.getElementById('personSearchModal');
    modal.style.display = 'block';

    if (!allPersons.length) {
        loadPersons();
    } else {
        displayPersons(allPersons);
    }

    setTimeout(() => {
        document.getElementById('personSearchInput').focus();
    }, 100);
}

function closePersonSearch() {
    document.getElementById('personSearchModal').style.display = 'none';
}

function searchPersons() {
    const term = document.getElementById('personSearchInput').value.toLowerCase().trim();
    if (term === '') {
        displayPersons(allPersons);
        return;
    }

    const filtered = allPersons.filter(p => {
        const fullName = (p.firstName || '').toLowerCase() + ' ' + (p.lastName || '').toLowerCase();
        const nationalCode = (p.nationalCode || '').toLowerCase();
        return fullName.includes(term) || nationalCode.includes(term);
    });
    displayPersons(filtered);
}

function clearPersonSearch() {
    document.getElementById('personSearchInput').value = '';
    displayPersons(allPersons);
    document.getElementById('personSearchInput').focus();
}

// =============================================
// ================ نوع نویسنده ================
// =============================================

function loadAuthorTypes() {
    fetch('/author/findAllAuthorTypes')
        .then(r => r.json())
        .then(data => {
            allAuthorTypes = data;
            displayAuthorTypes(data);
        })
        .catch(err => console.error('Error loading author types:', err));
}

function getAuthorTypeDisplayName(type) {
    return type.authorTypeRole || type.authorExpertise || type.role || 'نامشخص';
}

function displayAuthorTypes(types) {
    const tbody = document.getElementById('authorTypeTableBody');
    if (!tbody) return;

    let html = '';
    if (!types || types.length === 0) {
        html = `<tr><td colspan="4" class="empty-state">موردی یافت نشد</td></tr>`;
    } else {
        types.forEach(t => {
            const id = t.authorTypeId || t.id || '';
            const displayName = getAuthorTypeDisplayName(t);
            const createdBy = t.createdBy || 'نامشخص';
            html += `
                <tr onclick="selectAuthorType('${id}','${displayName.replace(/'/g, "\\'")}')">
                    <td>${id}</td>
                    <td>${displayName}</td>
                    <td>${createdBy}</td>
                    <td><button class="btn btn-success btn-sm">انتخاب</button></td>
                </tr>
            `;
        });
    }
    tbody.innerHTML = html;
}

function selectAuthorType(id, displayName) {
    document.getElementById('authorTypeId').value = id;
    const infoDiv = document.getElementById('selectedAuthorTypeInfo');
    infoDiv.classList.add('show');
    document.getElementById('selectedAuthorTypeTitle').textContent = displayName;
    closeAuthorTypeSearch();
    showMessage('نوع نویسنده با موفقیت انتخاب شد', 'success');
}

function openAuthorTypeSearch() {
    const modal = document.getElementById('authorTypeSearchModal');
    modal.style.display = 'block';

    if (!allAuthorTypes.length) {
        loadAuthorTypes();
    } else {
        displayAuthorTypes(allAuthorTypes);
    }

    setTimeout(() => {
        document.getElementById('authorTypeSearchInput').focus();
    }, 100);
}

function closeAuthorTypeSearch() {
    document.getElementById('authorTypeSearchModal').style.display = 'none';
}

function searchAuthorTypes() {
    const term = document.getElementById('authorTypeSearchInput').value.toLowerCase().trim();
    if (term === '') {
        displayAuthorTypes(allAuthorTypes);
        return;
    }

    const filtered = allAuthorTypes.filter(t => {
        const displayName = getAuthorTypeDisplayName(t).toLowerCase();
        return displayName.includes(term);
    });
    displayAuthorTypes(filtered);
}

function clearAuthorTypeSearch() {
    document.getElementById('authorTypeSearchInput').value = '';
    displayAuthorTypes(allAuthorTypes);
    document.getElementById('authorTypeSearchInput').focus();
}

// =============================================
// ================ ویرایش =====================
// =============================================

function openCreateModal() {
    document.getElementById('modalTitle').textContent = 'ثبت نویسنده جدید';
    document.getElementById('authorForm').reset();
    document.getElementById('selectedPersonInfo').classList.remove('show');
    document.getElementById('selectedAuthorTypeInfo').classList.remove('show');
    document.getElementById('authorId').value = '';
    document.getElementById('authorModal').style.display = 'block';
}

function openEditModal(button) {
    // پیدا کردن ردیف و شناسه نویسنده
    const row = button.closest('tr');
    if (!row) {
        showMessage('ردیف مورد نظر یافت نشد', 'error');
        return;
    }

    // روش دقیق‌تر برای گرفتن شناسه
    const cells = row.querySelectorAll('td');
    if (cells.length < 1) {
        showMessage('داده‌های ردیف نامعتبر است', 'error');
        return;
    }

    const authorId = cells[0].textContent.trim();
    console.log('🔍 شناسه نویسنده برای ویرایش:', authorId);

    if (!authorId || authorId === '') {
        showMessage('شناسه نویسنده معتبر نیست', 'error');
        return;
    }

    showMessage('در حال بارگذاری اطلاعات نویسنده...', 'info');

    // درخواست به سرور
    fetch(`/author/findAuthorById/${authorId}`)
        .then(response => {
            console.log('📡 وضعیت پاسخ:', response.status);
            if (!response.ok) {
                throw new Error(`خطای سرور: ${response.status}`);
            }
            return response.json();
        })
        .then(author => {
            console.log('✅ داده دریافت شده:', author);

            // بررسی وجود داده
            if (!author || Object.keys(author).length === 0) {
                throw new Error('داده‌ای دریافت نشد');
            }

            // تغییر عنوان مودال
            document.getElementById('modalTitle').textContent = 'ویرایش نویسنده';

            // تنظیم شناسه نویسنده (hidden field)
            const authorIdField = document.getElementById('authorId');
            if (authorIdField) {
                authorIdField.value = author.authorId || author.id || '';
            }

            // ========== نمایش اطلاعات شخص ==========
            const personId = author.personId || author.person?.personId || '';
            document.getElementById('personId').value = personId;

            // اطلاعات شخص (با چندین روش مختلف)
            const firstName = author.authorFirstName ||
                author.firstName ||
                author.person?.firstName ||
                '';
            const lastName = author.authorLastName ||
                author.lastName ||
                author.person?.lastName ||
                '';
            const nationalCode = author.authorNationalCode ||
                author.nationalCode ||
                author.person?.nationalCode ||
                '';

            // نمایش اطلاعات شخص
            const pInfo = document.getElementById('selectedPersonInfo');
            if (pInfo) {
                pInfo.classList.add('show');
                document.getElementById('selectedFirstName').textContent = firstName;
                document.getElementById('selectedLastName').textContent = lastName;
                document.getElementById('selectedNationalCode').textContent = nationalCode;
            }

            // ========== نمایش نوع نویسنده ==========
            const typeId = author.authorTypeId ||
                author.authorType?.authorTypeId ||
                author.authorType?.id ||
                '';
            document.getElementById('authorTypeId').value = typeId;

            // اطلاعات نوع نویسنده (با چندین روش مختلف)
            const typeName = author.authorTypeRole ||
                author.authorExpertise ||
                author.authorType?.role ||
                author.authorType?.authorTypeRole ||
                author.authorType?.name ||
                'نامشخص';

            // نمایش اطلاعات نوع نویسنده
            const tInfo = document.getElementById('selectedAuthorTypeInfo');
            if (tInfo) {
                tInfo.classList.add('show');
                document.getElementById('selectedAuthorTypeTitle').textContent = typeName;
            }

            // نمایش مودال
            document.getElementById('authorModal').style.display = 'block';

            showMessage('اطلاعات نویسنده با موفقیت بارگذاری شد', 'success');
        })
        .catch(error => {
            console.error('❌ خطا در ویرایش:', error);
            showMessage('خطا در بارگذاری اطلاعات: ' + error.message, 'error');
        });
}

function closeModal() {
    document.getElementById('authorModal').style.display = 'none';
}

// =============================================
// ================ پیام‌ها ====================
// =============================================

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
            if (alertDiv.parentNode) {
                alertDiv.remove();
            }
        }, 500);
    }, 5000);
}

// =============================================
// ================ رویدادها ===================
// =============================================

document.addEventListener('DOMContentLoaded', () => {
    loadPersons();
    loadAuthorTypes();

    // جستجوی خودکار برای شخص
    const personInput = document.getElementById('personSearchInput');
    if (personInput) {
        personInput.addEventListener('input', function() {
            if (personSearchTimeout) clearTimeout(personSearchTimeout);
            const term = this.value.trim();
            if (term.length >= 2 || term.length === 0) {
                personSearchTimeout = setTimeout(searchPersons, 400);
            }
        });
    }

    // جستجوی خودکار برای نوع نویسنده
    const authorTypeInput = document.getElementById('authorTypeSearchInput');
    if (authorTypeInput) {
        authorTypeInput.addEventListener('input', function() {
            if (authorTypeSearchTimeout) clearTimeout(authorTypeSearchTimeout);
            const term = this.value.trim();
            if (term.length >= 2 || term.length === 0) {
                authorTypeSearchTimeout = setTimeout(searchAuthorTypes, 400);
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

    console.log('🚀 صفحه مدیریت نویسندگان بارگذاری شد');
});