// receipt.js - مدیریت صفحه رسیدها

// ============================
// متغیرهای سراسری
// ============================

let allBooks = [];
let selectedBooks = [];
let bookSearchTimeout = null;
let isEditMode = false;

// ============================
// توابع کمکی
// ============================

function showMessage(message, type = 'success') {
    const container = document.querySelector('.container');
    if (!container) return;

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
        setTimeout(() => alertDiv.remove(), 500);
    }, 5000);
}

// ============================
// توابع مدیریت کتاب‌ها (چندگانه)
// ============================

function loadBooks(searchTerm) {
    const url = searchTerm ?
        `/receipt/findAllBooks?term=${encodeURIComponent(searchTerm)}` :
        '/receipt/findAllBooks';

    fetch(url)
        .then(r => {
            if (!r.ok) throw new Error('خطا در دریافت لیست کتاب‌ها');
            return r.json();
        })
        .then(data => {
            allBooks = data;
            displayBooks(data);
        })
        .catch(err => {
            console.error('Error loading books:', err);
            showMessage('خطا در بارگذاری لیست کتاب‌ها', 'error');
        });
}

function displayBooks(books) {
    const tbody = document.getElementById('bookTableBody');
    if (!tbody) return;

    let html = '';
    if (!books || books.length === 0) {
        html = `<tr><td colspan="5" class="empty-state">کتابی یافت نشد</td></tr>`;
    } else {
        books.forEach(b => {
            const checked = selectedBooks.some(s => s.bookId === b.bookId) ? 'checked' : '';
            const bookTitle = b.bookTitle || '';
            const isbn = b.isbn || '';
            const authors = (b.authors || []).map(a => (a.firstName || '') + ' ' + (a.lastName || '')).join('، ');
            const escapedTitle = bookTitle.replace(/'/g, "\\'");

            html += `
                <tr>
                    <td>${b.bookId}</td>
                    <td>${bookTitle}</td>
                    <td>${isbn}</td>
                    <td>${authors || '—'}</td>
                    <td>
                        <input type="checkbox" ${checked}
                               onchange="toggleBook(this, ${b.bookId}, '${escapedTitle}')">
                    </td>
                </tr>
            `;
        });
    }
    tbody.innerHTML = html;
}

function toggleBook(checkbox, id, title) {
    if (checkbox.checked) {
        if (!selectedBooks.some(b => b.bookId === id)) {
            selectedBooks.push({
                bookId: id,
                bookTitle: title
            });
        }
    } else {
        selectedBooks = selectedBooks.filter(b => b.bookId !== id);
    }
    renderSelectedBooks();
}

function searchBooks() {
    const term = document.getElementById('bookSearchInput').value.trim();
    loadBooks(term);
}

function clearBookSearch() {
    document.getElementById('bookSearchInput').value = '';
    loadBooks('');
    document.getElementById('bookSearchInput').focus();
}

function openBookSearch() {
    const modal = document.getElementById('bookSearchModal');
    modal.style.display = 'block';

    if (!allBooks.length) {
        loadBooks('');
    }

    setTimeout(() => {
        document.getElementById('bookSearchInput').focus();
    }, 100);
}

function closeBookSearch() {
    document.getElementById('bookSearchModal').style.display = 'none';
}

function confirmSelectedBooks() {
    renderSelectedBooks();
    closeBookSearch();
    showMessage(`${selectedBooks.length} کتاب انتخاب شد`, 'success');
}

function renderSelectedBooks() {
    const container = document.getElementById('selectedBooksContainer');
    if (!container) return;

    let html = '';
    if (selectedBooks.length === 0) {
        html = '<span style="color:#888;">هیچ کتابی انتخاب نشده</span>';
    } else {
        selectedBooks.forEach((b, i) => {
            html += `
                <span class="book-tag">
                    ${b.bookTitle || 'بدون عنوان'}
                    <button onclick="removeBook(${i})" class="remove-role" title="حذف کتاب">×</button>
                </span>
            `;
        });
    }
    container.innerHTML = html;

    const display = document.getElementById('booksDisplay');
    if (display) {
        display.value = selectedBooks.length ?
            `${selectedBooks.length} کتاب انتخاب شده` :
            'هیچ کتابی انتخاب نشده';
    }

    updateBookIdsInput();
}

function removeBook(index) {
    selectedBooks.splice(index, 1);
    renderSelectedBooks();
}

function updateBookIdsInput() {
    const container = document.getElementById('bookIdsContainer');
    if (!container) return;

    container.innerHTML = '';

    selectedBooks.forEach((book, index) => {
        const input = document.createElement('input');
        input.type = 'hidden';
        input.name = `books[${index}].bookId`;
        input.value = book.bookId;
        container.appendChild(input);
    });
}

// ============================
// توابع مدیریت فرم
// ============================

function openCreateModal() {
    console.log('openCreateModal called');

    const modal = document.getElementById('receiptModal');
    if (!modal) {
        console.error('receiptModal not found');
        return;
    }

    const title = document.getElementById('modalTitle');
    const form = document.getElementById('receiptForm');
    const submitBtn = document.getElementById('submitBtn');

    if (title) title.textContent = 'ثبت رسید جدید';
    if (submitBtn) {
        submitBtn.textContent = 'ذخیره';
        submitBtn.className = 'btn btn-success';
    }
    if (form) form.reset();
    isEditMode = false;

    const receiptId = document.getElementById('receiptId');
    if (receiptId) receiptId.value = '';

    const formAction = document.getElementById('formAction');
    if (formAction) formAction.value = 'save';

    if (form) form.action = '/receipt/saveReceipt';

    // Reset selections
    selectedBooks = [];
    renderSelectedBooks();

    modal.style.display = 'block';
}

function openEditModal(button) {
    const receiptId = button.getAttribute('data-id');

    if (!receiptId) {
        showMessage('شناسه رسید یافت نشد', 'error');
        return;
    }

    showMessage('در حال بارگذاری اطلاعات رسید...', 'info');

    fetch(`/receipt/findReceiptById/${receiptId}`)
        .then(r => {
            if (!r.ok) throw new Error('خطا در دریافت اطلاعات رسید');
            return r.json();
        })
        .then(receipt => {
            const modal = document.getElementById('receiptModal');
            if (!modal) return;

            const title = document.getElementById('modalTitle');
            const submitBtn = document.getElementById('submitBtn');
            const form = document.getElementById('receiptForm');

            if (title) title.textContent = 'ویرایش رسید';
            if (submitBtn) {
                submitBtn.textContent = 'به‌روزرسانی';
                submitBtn.className = 'btn btn-primary';
            }
            isEditMode = true;

            const receiptIdInput = document.getElementById('receiptId');
            if (receiptIdInput) receiptIdInput.value = receipt.receiptId || '';

            const formAction = document.getElementById('formAction');
            if (formAction) formAction.value = 'update';

            if (form) form.action = '/receipt/updateReceipt';

            // Set books
            selectedBooks = (receipt.books || []).map(b => ({
                bookId: b.bookId,
                bookTitle: b.bookTitle || ''
            }));
            renderSelectedBooks();

            modal.style.display = 'block';
            showMessage('اطلاعات رسید بارگذاری شد', 'success');
        })
        .catch(err => {
            console.error('Error loading receipt:', err);
            showMessage('خطا در بارگذاری اطلاعات رسید: ' + err.message, 'error');
        });
}

function closeModal() {
    const modal = document.getElementById('receiptModal');
    if (modal) modal.style.display = 'none';
}

// ============================
// ارسال فرم
// ============================

function submitReceiptForm(event) {
    if (event) {
        event.preventDefault();
    }

    if (selectedBooks.length === 0) {
        showMessage('لطفاً حداقل یک کتاب انتخاب کنید', 'error');
        return false;
    }

    const form = document.getElementById('receiptForm');
    if (form) form.submit();
}

// ============================
// رویدادها
// ============================

document.addEventListener('DOMContentLoaded', function() {
    console.log('🚀 صفحه مدیریت رسیدها بارگذاری شد');

    // بارگذاری اولیه داده‌ها
    loadBooks('');

    // جستجوی کتاب
    const bookSearchInput = document.getElementById('bookSearchInput');
    if (bookSearchInput) {
        bookSearchInput.addEventListener('input', function() {
            if (bookSearchTimeout) {
                clearTimeout(bookSearchTimeout);
                bookSearchTimeout = null;
            }

            const term = this.value.trim();
            if (term.length >= 2 || term.length === 0) {
                bookSearchTimeout = setTimeout(() => {
                    loadBooks(term);
                    bookSearchTimeout = null;
                }, 400);
            }
        });
    }

    // ارسال فرم
    const form = document.getElementById('receiptForm');
    if (form) {
        form.addEventListener('submit', submitReceiptForm);
    }

    // بستن مودال‌ها با کلیک خارج از آنها
    window.onclick = function(event) {
        if (event.target.classList.contains('modal')) {
            event.target.style.display = 'none';
        }
    };

    // بستن مودال‌ها با کلید Escape
    document.addEventListener('keydown', function(event) {
        if (event.key === 'Escape') {
            document.querySelectorAll('.modal').forEach(modal => {
                if (modal.style.display === 'block') {
                    modal.style.display = 'none';
                }
            });
        }
    });
});