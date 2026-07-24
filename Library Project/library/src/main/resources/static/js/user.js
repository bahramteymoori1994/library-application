// user.js - مدیریت صفحه کاربران

// ============================
// متغیرهای سراسری
// ============================

let allPersons = [];
let allRoles = [];
let selectedRoles = [];
let personSearchTimeout = null;
let roleSearchTimeout = null;

// ============================
// توابع مدیریت اشخاص
// ============================

function loadPersons() {
    fetch('/user/findAllPeople')
        .then(r => {
            if (!r.ok) throw new Error('خطا در دریافت لیست اشخاص');
            return r.json();
        })
        .then(data => {
            allPersons = data;
            displayPersons(data);
        })
        .catch(err => {
            console.error('Error loading persons:', err);
            showMessage('خطا در بارگذاری لیست اشخاص', 'error');
        });
}

function displayPersons(persons) {
    const tbody = document.getElementById('personTableBody');
    if (!tbody) return;

    let html = '';
    if (!persons || persons.length === 0) {
        html = `<tr><td colspan="5" class="empty-state">موردی یافت نشد</td></tr>`;
    } else {
        persons.forEach(p => {
            html += `
                <tr onclick="selectPerson('${p.personId}','${p.firstName || ''}','${p.lastName || ''}','${p.nationalCode || ''}')">
                    <td>${p.personId}</td>
                    <td>${p.firstName || ''}</td>
                    <td>${p.lastName || ''}</td>
                    <td>${p.nationalCode || ''}</td>
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
    }

    setTimeout(() => {
        document.getElementById('searchInput').focus();
    }, 100);
}

function closePersonSearch() {
    document.getElementById('personSearchModal').style.display = 'none';
}

function searchPersons() {
    const term = document.getElementById('searchInput').value.toLowerCase().trim();
    if (term === '') {
        displayPersons(allPersons);
        return;
    }

    const filtered = allPersons.filter(p =>
        (p.firstName || '').toLowerCase().includes(term) ||
        (p.lastName || '').toLowerCase().includes(term) ||
        (p.nationalCode || '').includes(term)
    );
    displayPersons(filtered);
}

function clearSearch() {
    document.getElementById('searchInput').value = '';
    displayPersons(allPersons);
    document.getElementById('searchInput').focus();
}

// ============================
// توابع مدیریت نقش‌ها
// ============================

function loadRoles(searchTerm) {
    const url = searchTerm ?
        `/user/searchRoles?term=${encodeURIComponent(searchTerm)}` :
        '/user/findAllRoles';

    fetch(url)
        .then(r => {
            if (!r.ok) throw new Error('خطا در دریافت لیست نقش‌ها');
            return r.json();
        })
        .then(data => {
            allRoles = data;
            displayRoles(data);
        })
        .catch(err => {
            console.error('Error loading roles:', err);
            showMessage('خطا در بارگذاری لیست نقش‌ها', 'error');
        });
}

function displayRoles(roles) {
    const tbody = document.getElementById('roleTableBody');
    if (!tbody) return;

    let html = '';
    if (!roles || roles.length === 0) {
        html = `<tr><td colspan="4" class="empty-state">نقشی یافت نشد</td></tr>`;
    } else {
        roles.forEach(r => {
            const checked = selectedRoles.some(s => s.roleId === r.roleId) ? 'checked' : '';
            html += `
                <tr>
                    <td>${r.roleId}</td>
                    <td>${r.englishRoleTitle || ''}</td>
                    <td>${r.farsiRoleTitle || ''}</td>
                    <td>
                        <input type="checkbox" ${checked}
                               onchange="toggleRole(this, ${r.roleId}, '${r.englishRoleTitle || ''}', '${r.farsiRoleTitle || ''}')">
                    </td>
                </tr>
            `;
        });
    }
    tbody.innerHTML = html;
}

function toggleRole(checkbox, id, englishTitle, farsiTitle) {
    if (checkbox.checked) {
        if (!selectedRoles.some(r => r.roleId === id)) {
            selectedRoles.push({
                roleId: id,
                englishRoleTitle: englishTitle,
                farsiRoleTitle: farsiTitle
            });
        }
    } else {
        selectedRoles = selectedRoles.filter(r => r.roleId !== id);
    }
    renderSelectedRoles();
}

function searchRoles() {
    const term = document.getElementById('roleSearchInput').value.trim();
    loadRoles(term);
}

function clearRoleSearch() {
    document.getElementById('roleSearchInput').value = '';
    loadRoles('');
    document.getElementById('roleSearchInput').focus();
}

function openRoleSearch() {
    const modal = document.getElementById('roleSearchModal');
    modal.style.display = 'block';

    if (!allRoles.length) {
        loadRoles('');
    }

    setTimeout(() => {
        document.getElementById('roleSearchInput').focus();
    }, 100);
}

function closeRoleSearch() {
    document.getElementById('roleSearchModal').style.display = 'none';
}

function confirmSelectedRoles() {
    renderSelectedRoles();
    closeRoleSearch();
    showMessage(`${selectedRoles.length} نقش انتخاب شد`, 'success');
}

function renderSelectedRoles() {
    const container = document.getElementById('selectedRolesContainer');
    if (!container) return;

    let html = '';
    if (selectedRoles.length === 0) {
        html = '<span style="color:#888;">هیچ نقشی انتخاب نشده</span>';
    } else {
        selectedRoles.forEach((r, i) => {
            const displayName = r.farsiRoleTitle || r.englishRoleTitle || 'بدون عنوان';
            html += `
                <span class="role-tag">
                    ${displayName}
                    <button onclick="removeRole(${i})" class="remove-role" title="حذف نقش">×</button>
                </span>
            `;
        });
    }
    container.innerHTML = html;

    const display = document.getElementById('rolesDisplay');
    if (display) {
        display.value = selectedRoles.length ?
            `${selectedRoles.length} نقش انتخاب شده` :
            'هیچ نقشی انتخاب نشده';
    }

    updateRoleIdsInput();
}

function removeRole(index) {
    selectedRoles.splice(index, 1);
    renderSelectedRoles();
}

function updateRoleIdsInput() {
    const container = document.getElementById('roleIdsContainer');
    if (!container) return;

    container.innerHTML = '';

    selectedRoles.forEach(role => {
        const input = document.createElement('input');
        input.type = 'hidden';
        input.name = 'roleIds';
        input.value = role.roleId;
        container.appendChild(input);
    });
}

// ============================
// توابع مدیریت فرم
// ============================

function openCreateModal() {
    const modal = document.getElementById('userModal');
    const title = document.getElementById('modalTitle');
    const form = document.getElementById('userForm');

    title.textContent = 'ثبت کاربر جدید';
    form.reset();
    document.getElementById('userId').value = '';
    document.getElementById('selectedPersonInfo').classList.remove('show');
    selectedRoles = [];
    renderSelectedRoles();

    modal.style.display = 'block';

    setTimeout(() => {
        document.getElementById('username').focus();
    }, 100);
}

function openEditModal(button) {
    const row = button.closest('tr');
    const userId = row.querySelector('td:first-child').textContent.trim();

    // نمایش وضعیت بارگذاری
    showMessage('در حال بارگذاری اطلاعات کاربر...', 'info');

    fetch(`/user/findUserById/${userId}`)
        .then(r => {
            if (!r.ok) throw new Error('خطا در دریافت اطلاعات کاربر');
            return r.json();
        })
        .then(user => {
            const modal = document.getElementById('userModal');
            const title = document.getElementById('modalTitle');

            title.textContent = 'ویرایش کاربر';

            // پر کردن فرم
            document.getElementById('userId').value = user.userId || '';
            document.getElementById('username').value = user.username || '';
            document.getElementById('password').value = '';
            document.getElementById('email').value = user.email || '';

            // شخص
            if (user.person) {
                document.getElementById('personId').value = user.person.personId || '';
                document.getElementById('selectedPersonInfo').classList.add('show');
                document.getElementById('selectedFirstName').textContent = user.person.firstName || '';
                document.getElementById('selectedLastName').textContent = user.person.lastName || '';
                document.getElementById('selectedNationalCode').textContent = user.person.nationalCode || '';
            } else {
                document.getElementById('personId').value = '';
                document.getElementById('selectedPersonInfo').classList.remove('show');
            }

            // نقش‌ها
            selectedRoles = (user.roles || []).map(r => ({
                roleId: r.roleId,
                englishRoleTitle: r.englishRoleTitle || '',
                farsiRoleTitle: r.farsiRoleTitle || ''
            }));
            renderSelectedRoles();

            modal.style.display = 'block';
            showMessage('اطلاعات کاربر بارگذاری شد', 'success');
        })
        .catch(err => {
            console.error('Error loading user:', err);
            showMessage('خطا در بارگذاری اطلاعات کاربر: ' + err.message, 'error');
        });
}

function closeModal() {
    document.getElementById('userModal').style.display = 'none';
}

// ============================
// توابع کمکی
// ============================

function showMessage(message, type = 'success') {
    const container = document.querySelector('.container');
    if (!container) return;

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
        setTimeout(() => alertDiv.remove(), 500);
    }, 5000);
}

// ============================
// رویدادها
// ============================

document.addEventListener('DOMContentLoaded', function() {
    // بارگذاری داده‌ها
    loadPersons();
    loadRoles('');

    // جستجوی خودکار نقش‌ها
    const roleSearchInput = document.getElementById('roleSearchInput');
    if (roleSearchInput) {
        roleSearchInput.addEventListener('input', function() {
            if (roleSearchTimeout) {
                clearTimeout(roleSearchTimeout);
                roleSearchTimeout = null;
            }

            const term = this.value.trim();
            if (term.length >= 2 || term.length === 0) {
                roleSearchTimeout = setTimeout(() => {
                    loadRoles(term);
                    roleSearchTimeout = null;
                }, 400);
            }
        });
    }

    // جستجوی خودکار اشخاص
    const personSearchInput = document.getElementById('searchInput');
    if (personSearchInput) {
        personSearchInput.addEventListener('input', function() {
            if (personSearchTimeout) {
                clearTimeout(personSearchTimeout);
                personSearchTimeout = null;
            }

            const term = this.value.trim();
            if (term.length >= 2 || term.length === 0) {
                personSearchTimeout = setTimeout(() => {
                    searchPersons();
                    personSearchTimeout = null;
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

    // بستن مودال با ESC
    document.addEventListener('keydown', function(event) {
        if (event.key === 'Escape') {
            document.querySelectorAll('.modal').forEach(modal => {
                if (modal.style.display === 'block') {
                    modal.style.display = 'none';
                }
            });
        }
    });

    // جستجو با Enter
    const searchInput = document.getElementById('searchInput');
    if (searchInput) {
        searchInput.addEventListener('keypress', function(e) {
            if (e.key === 'Enter') {
                e.preventDefault();
                searchPersons();
            }
        });
    }

    const roleSearchInput2 = document.getElementById('roleSearchInput');
    if (roleSearchInput2) {
        roleSearchInput2.addEventListener('keypress', function(e) {
            if (e.key === 'Enter') {
                e.preventDefault();
                searchRoles();
            }
        });
    }

    console.log('🚀 صفحه مدیریت کاربران بارگذاری شد');
});