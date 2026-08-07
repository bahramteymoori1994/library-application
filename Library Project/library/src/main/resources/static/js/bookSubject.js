// book-subject.js - مدیریت صفحه موضوعات کتاب

// ============================
// متغیرهای سراسری
// ============================

let allBookSubjects = [];
let bookSubjectSearchTimeout = null;
let selectedParentSubject = null;

// ============================
// توابع مدیریت موضوعات کتاب
// ============================

function loadBookSubjects() {
    fetch('/bookSubject/findAllBookSubjects')
        .then(r => {
            if (!r.ok) throw new Error('خطا در دریافت لیست موضوعات کتاب');
            return r.json();
        })
        .then(data => {
            allBookSubjects = data;
            displayBookSubjects(data);
        })
        .catch(err => {
            console.error('Error loading book subjects:', err);
            showMessage('خطا در بارگذاری لیست موضوعات کتاب', 'error');
        });
}

function displayBookSubjects(subjects) {
    const tbody = document.getElementById('bookSubjectTableBody');
    if (!tbody) return;

    let html = '';
    if (!subjects || subjects.length === 0) {
        html = `<tr><td colspan="5" class="empty-state">موردی یافت نشد</td></tr>`;
    } else {
        subjects.forEach(s => {
            const parentTitle = s.bookSubject ? s.bookSubject.subjectTitle : '—';
            html += `
                <tr onclick="selectBookSubject('${s.bookSubjectId}','${s.subjectTitle || ''}')">
                    <td>${s.bookSubjectId}</td>
                    <td>${s.subjectTitle || ''}</td>
                    <td>${parentTitle}</td>
                    <td>${s.createdDate || ''}</td>
                    <td><button class="btn btn-success btn-sm">انتخاب</button></td>
                </tr>
            `;
        });
    }
    tbody.innerHTML = html;
}

function selectBookSubject(id, title) {
    document.getElementById('bookSubjectId').value = id;

    const infoDiv = document.getElementById('selectedBookSubjectInfo');
    infoDiv.classList.add('show');
    document.getElementById('selectedSubjectTitle').textContent = title;

    // اگر والد هم انتخاب شده باشد، آن را نیز نمایش می‌دهیم
    const selectedSubject = allBookSubjects.find(s => s.bookSubjectId == id);
    if (selectedSubject && selectedSubject.bookSubject) {
        document.getElementById('selectedParentTitle').textContent = selectedSubject.bookSubject.subjectTitle || '—';
    } else {
        document.getElementById('selectedParentTitle').textContent = '—';
    }

    closeBookSubjectSearch();
    showMessage('موضوع کتاب با موفقیت انتخاب شد', 'success');
}

function openBookSubjectSearch() {
    const modal = document.getElementById('bookSubjectSearchModal');
    modal.style.display = 'block';

    if (!allBookSubjects.length) {
        loadBookSubjects();
    }

    setTimeout(() => {
        document.getElementById('bookSubjectSearchInput').focus();
    }, 100);
}

function closeBookSubjectSearch() {
    document.getElementById('bookSubjectSearchModal').style.display = 'none';
}

function searchBookSubjects() {
    const term = document.getElementById('bookSubjectSearchInput').value.toLowerCase().trim();
    if (term === '') {
        displayBookSubjects(allBookSubjects);
        return;
    }

    const filtered = allBookSubjects.filter(s =>
        (s.subjectTitle || '').toLowerCase().includes(term) ||
        (s.bookSubject && (s.bookSubject.subjectTitle || '').toLowerCase().includes(term))
    );
    displayBookSubjects(filtered);
}

function clearBookSubjectSearch() {
    document.getElementById('bookSubjectSearchInput').value = '';
    displayBookSubjects(allBookSubjects);
    document.getElementById('bookSubjectSearchInput').focus();
}

// ============================
// توابع مدیریت فرم
// ============================

function openCreateBookSubjectModal() {
    const modal = document.getElementById('bookSubjectModal');
    const title = document.getElementById('bookSubjectModalTitle');
    const form = document.getElementById('bookSubjectForm');

    title.textContent = 'ثبت موضوع کتاب جدید';
    form.reset();
    document.getElementById('bookSubjectId').value = '';
    document.getElementById('selectedBookSubjectInfo').classList.remove('show');
    selectedParentSubject = null;

    modal.style.display = 'block';

    setTimeout(() => {
        document.getElementById('subjectTitle').focus();
    }, 100);
}

function openEditBookSubjectModal(button) {
    const row = button.closest('tr');
    const subjectId = row.querySelector('td:first-child').textContent.trim();

    // نمایش وضعیت بارگذاری
    showMessage('در حال بارگذاری اطلاعات موضوع کتاب...', 'info');

    fetch(`/bookSubject/findBookSubjectById/${subjectId}`)
        .then(r => {
            if (!r.ok) throw new Error('خطا در دریافت اطلاعات موضوع کتاب');
            return r.json();
        })
        .then(subject => {
            const modal = document.getElementById('bookSubjectModal');
            const title = document.getElementById('bookSubjectModalTitle');

            title.textContent = 'ویرایش موضوع کتاب';

            // پر کردن فرم
            document.getElementById('bookSubjectId').value = subject.bookSubjectId || '';
            document.getElementById('subjectTitle').value = subject.subjectTitle || '';

            // والد
            if (subject.bookSubject) {
                document.getElementById('bookSubjectId').value = subject.bookSubject.bookSubjectId || '';
                document.getElementById('selectedBookSubjectInfo').classList.add('show');
                document.getElementById('selectedSubjectTitle').textContent = subject.bookSubject.subjectTitle || '';
                document.getElementById('selectedParentTitle').textContent = subject.bookSubject.bookSubject ?
                    subject.bookSubject.bookSubject.subjectTitle || '—' : '—';
                selectedParentSubject = subject.bookSubject;
            } else {
                document.getElementById('bookSubjectId').value = '';
                document.getElementById('selectedBookSubjectInfo').classList.remove('show');
                selectedParentSubject = null;
            }

            modal.style.display = 'block';
            showMessage('اطلاعات موضوع کتاب بارگذاری شد', 'success');
        })
        .catch(err => {
            console.error('Error loading book subject:', err);
            showMessage('خطا در بارگذاری اطلاعات موضوع کتاب: ' + err.message, 'error');
        });
}

function closeBookSubjectModal() {
    document.getElementById('bookSubjectModal').style.display = 'none';
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
    loadBookSubjects();

    // جستجوی خودکار موضوعات کتاب
    const searchInput = document.getElementById('bookSubjectSearchInput');
    if (searchInput) {
        searchInput.addEventListener('input', function() {
            if (bookSubjectSearchTimeout) {
                clearTimeout(bookSubjectSearchTimeout);
                bookSubjectSearchTimeout = null;
            }

            const term = this.value.trim();
            if (term.length >= 2 || term.length === 0) {
                bookSubjectSearchTimeout = setTimeout(() => {
                    searchBookSubjects();
                    bookSubjectSearchTimeout = null;
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
    const searchInput2 = document.getElementById('bookSubjectSearchInput');
    if (searchInput2) {
        searchInput2.addEventListener('keypress', function(e) {
            if (e.key === 'Enter') {
                e.preventDefault();
                searchBookSubjects();
            }
        });
    }

    console.log('🚀 صفحه مدیریت موضوعات کتاب بارگذاری شد');
});