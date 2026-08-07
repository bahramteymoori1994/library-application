// author.js - مدیریت صفحه نویسندگان

let allAuthorTypes = [];
let authorTypeSearchTimeout = null;

// =============================================
// ================ نوع نویسنده ================
// =============================================

function loadAuthorTypes() {
    fetch('/author/findAllAuthorTypes')
        .then(r => {
            if (!r.ok) throw new Error('خطا در دریافت لیست نوع نویسنده');
            return r.json();
        })
        .then(data => {
            allAuthorTypes = data;
            displayAuthorTypes(data);
        })
        .catch(err => {
            console.error('Error loading author types:', err);
            showMessage('خطا در بارگذاری نوع نویسنده', 'error');
        });
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
            const title = t.role || t.authorTypeRole || t.title || 'بدون عنوان';
            const description = t.description || t.authorTypeDescription || '—';
            html += `
                <tr onclick="selectAuthorType('${id}','${title.replace(/'/g, "\\'")}')">
                    <td>${id}</td>
                    <td>${title}</td>
                    <td>${description}</td>
                    <td><button class="btn btn-success btn-sm">انتخاب</button></td>
                </tr>
            `;
        });
    }
    tbody.innerHTML = html;
}

function selectAuthorType(id, title) {
    document.getElementById('authorTypeId').value = id;
    const infoDiv = document.getElementById('selectedAuthorTypeInfo');
    infoDiv.classList.add('show');
    document.getElementById('selectedAuthorTypeTitle').textContent = title;
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
        const title = (t.role || t.authorTypeRole || t.title || '').toLowerCase();
        const description = (t.description || t.authorTypeDescription || '').toLowerCase();
        return title.includes(term) || description.includes(term);
    });
    displayAuthorTypes(filtered);
}

function clearAuthorTypeSearch() {
    document.getElementById('authorTypeSearchInput').value = '';
    displayAuthorTypes(allAuthorTypes);
    document.getElementById('authorTypeSearchInput').focus();
}

// =============================================
// ================ مدیریت فرم =================
// =============================================

function openCreateModal() {
    const modal = document.getElementById('authorModal');
    const title = document.getElementById('modalTitle');
    const form = document.getElementById('authorForm');

    title.textContent = 'ثبت نویسنده جدید';
    form.reset();
    document.getElementById('authorId').value = '';
    document.getElementById('selectedAuthorTypeInfo').classList.remove('show');

    modal.style.display = 'block';

    setTimeout(() => {
        document.getElementById('firstName').focus();
    }, 100);
}

function openEditModal(button) {
    const row = button.closest('tr');
    if (!row) {
        showMessage('ردیف مورد نظر یافت نشد', 'error');
        return;
    }

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

            if (!author || Object.keys(author).length === 0) {
                throw new Error('داده‌ای دریافت نشد');
            }

            // تغییر عنوان مودال
            document.getElementById('modalTitle').textContent = 'ویرایش نویسنده';

            // تنظیم شناسه نویسنده
            document.getElementById('authorId').value = author.authorId || '';

            // تنظیم نام و نام خانوادگی
            document.getElementById('firstName').value = author.firstName || '';
            document.getElementById('lastName').value = author.lastName || '';

            // ========== نمایش نوع نویسنده ==========
            const typeId = author.authorType?.authorTypeId ||
                author.authorTypeId ||
                '';
            document.getElementById('authorTypeId').value = typeId;

            // اطلاعات نوع نویسنده
            const typeName = author.authorType?.role ||
                author.authorType?.authorTypeRole ||
                author.authorType?.title ||
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
    loadAuthorTypes();

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