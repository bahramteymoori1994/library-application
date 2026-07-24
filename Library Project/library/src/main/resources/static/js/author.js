// author.js
let allPersons = [];
let allAuthorTypes = [];
let selectedPersonId = null;
let selectedAuthorTypeId = null;

// ====================== اشخاص ======================
function loadPersons() {
    fetch('/author/findAllPeople')
        .then(r => r.json())
        .then(data => {
            allPersons = data;
            displayPersons(data);
        })
        .catch(err => console.error(err));
}

function displayPersons(persons) {
    const tbody = document.getElementById('personTableBody');
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
                </tr>`;
        });
    }
    tbody.innerHTML = html;
}

function selectPerson(id, firstName, lastName, nationalCode) {
    selectedPersonId = id;
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
    if (!allPersons.length) loadPersons();
    setTimeout(() => document.getElementById('searchInput').focus(), 100);
}

function closePersonSearch() {
    document.getElementById('personSearchModal').style.display = 'none';
}

function searchPersons() {
    const term = document.getElementById('searchInput').value.toLowerCase().trim();
    const filtered = term === '' ? allPersons : allPersons.filter(p =>
        (p.firstName || '').toLowerCase().includes(term) ||
        (p.lastName || '').toLowerCase().includes(term) ||
        (p.nationalCode || '').includes(term)
    );
    displayPersons(filtered);
}

// ====================== نوع نویسنده ======================
function loadAuthorTypes() {
    fetch('/author/findAllAuthorTypes')
        .then(r => r.json())
        .then(data => {
            allAuthorTypes = data;
            displayAuthorTypes(data);
        });
}

function displayAuthorTypes(types) {
    const tbody = document.getElementById('authorTypeTableBody');
    let html = '';
    if (!types || types.length === 0) {
        html = `<tr><td colspan="4" class="empty-state">موردی یافت نشد</td></tr>`;
    } else {
        types.forEach(t => {
            const checked = selectedAuthorTypeId === t.authorTypeId ? 'checked' : '';
            html += `
                <tr>
                    <td>${t.authorTypeId}</td>
                    <td>${t.title || ''}</td>
                    <td>${t.description || ''}</td>
                    <td>
                        <input type="radio" name="authorTypeSelect" ${checked}
                               onchange="selectAuthorType(${t.authorTypeId}, '${t.title || ''}')">
                    </td>
                </tr>`;
        });
    }
    tbody.innerHTML = html;
}

function selectAuthorType(id, title) {
    selectedAuthorTypeId = id;
    document.getElementById('authorTypeId').value = id;

    const infoDiv = document.getElementById('selectedAuthorTypeInfo');
    infoDiv.classList.add('show');
    document.getElementById('selectedAuthorTypeTitle').textContent = title;

    closeAuthorTypeSearch();
    showMessage('نوع نویسنده انتخاب شد', 'success');
}

function openAuthorTypeSearch() {
    const modal = document.getElementById('authorTypeSearchModal');
    modal.style.display = 'block';
    if (!allAuthorTypes.length) loadAuthorTypes();
    setTimeout(() => document.getElementById('authorTypeSearchInput').focus(), 100);
}

function closeAuthorTypeSearch() {
    document.getElementById('authorTypeSearchModal').style.display = 'none';
}

function searchAuthorTypes() {
    const term = document.getElementById('authorTypeSearchInput').value.toLowerCase().trim();
    const filtered = term === '' ? allAuthorTypes : allAuthorTypes.filter(t =>
        (t.title || '').toLowerCase().includes(term) ||
        (t.description || '').toLowerCase().includes(term)
    );
    displayAuthorTypes(filtered);
}

// ====================== توابع عمومی ======================
function showMessage(message, type = 'success') {
    const container = document.querySelector('.container');
    const alertDiv = document.createElement('div');
    alertDiv.className = `alert alert-${type}`;
    alertDiv.textContent = message;
    container.insertBefore(alertDiv, container.firstChild);

    setTimeout(() => alertDiv.remove(), 5000);
}

document.addEventListener('DOMContentLoaded', function() {
    // بارگذاری اولیه
    loadPersons();
    loadAuthorTypes();

    // جستجوی زنده
    const personInput = document.getElementById('searchInput');
    if (personInput) personInput.addEventListener('input', () => {
        clearTimeout(window.personTimeout);
        window.personTimeout = setTimeout(searchPersons, 400);
    });

    const typeInput = document.getElementById('authorTypeSearchInput');
    if (typeInput) typeInput.addEventListener('input', () => {
        clearTimeout(window.typeTimeout);
        window.typeTimeout = setTimeout(searchAuthorTypes, 400);
    });

    console.log('🚀 صفحه مدیریت نویسندگان بارگذاری شد');
});