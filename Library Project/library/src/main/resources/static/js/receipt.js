// receipt.js - مدیریت صفحه رسیدها

// ============================
// متغیرهای سراسری
// ============================

let allBooks = [];
let selectedBooks = [];
let bookSearchTimeout = null;
let isEditMode = false;
let allReceipts = [];
let currentUsername = '';

// ============================
// توابع کمکی
// ============================

function showMessage(message, type = 'success') {
    const container = document.getElementById('messageContainer');
    if (!container) return;

    container.innerHTML = '';

    const alertDiv = document.createElement('div');
    alertDiv.className = `alert alert-${type}`;
    alertDiv.textContent = message;

    container.appendChild(alertDiv);

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

function updateResultCount(count, username = null) {
    const span = document.getElementById('resultCount');
    if (!span) return;

    if (username) {
        span.textContent = `🔹 ${count} رسید برای کاربر "${username}"`;
    } else {
        span.textContent = `🔹 ${count} رسید یافت شد`;
    }
}

// ============================
// توابع اصلی بارگذاری رسیدها
// ============================

function loadReceiptsByUsername() {
    const usernameInput = document.getElementById('usernameFilter');
    const username = usernameInput ? usernameInput.value.trim() : '';

    if (!username) {
        showMessage('لطفاً نام کاربری را وارد کنید', 'error');
        return;
    }

    currentUsername = username;
    console.log(`📥 در حال بارگذاری رسیدهای کاربر: ${username}`);

    fetch(`/receipt/findAllReceiptsView/${encodeURIComponent(username)}`)
        .then(response => {
            if (!response.ok) {
                throw new Error(`خطا در دریافت رسیدها: ${response.status}`);
            }
            return response.json();
        })
        .then(data => {
            console.log(`✅ رسیدهای کاربر ${username} دریافت شدند:`, data);
            allReceipts = data;
            renderReceiptsTable(data);
            updateResultCount(data.length, username);

            if (data.length === 0) {
                showMessage(`هیچ رسیدی برای کاربر "${username}" یافت نشد`, 'info');
            } else {
                showMessage(`${data.length} رسید برای کاربر "${username}" یافت شد`, 'success');
            }
        })
        .catch(error => {
            console.error('❌ خطا در بارگذاری رسیدها:', error);
            showMessage('خطا در بارگذاری لیست رسیدها: ' + error.message, 'error');

            const tbody = document.getElementById('receiptTableBody');
            if (tbody) {
                tbody.innerHTML = `
                    <tr>
                        <td colspan="13" class="empty-state">
                            <div>⚠️</div>
                            <div style="margin-top: 10px;">خطا در بارگذاری داده‌ها</div>
                            <div style="font-size: 12px; color: #888; margin-top: 5px;">${error.message}</div>
                        </td>
                    </tr>
                `;
            }
            updateResultCount(0);
        });
}

function loadAllReceipts() {
    console.log('📥 در حال بارگذاری همه رسیدها...');

    const usernameInput = document.getElementById('usernameFilter');
    if (usernameInput) {
        usernameInput.value = '';
    }
    currentUsername = '';

    fetch('/receipt/findAllReceiptsView')
        .then(response => {
            if (!response.ok) {
                throw new Error(`خطا در دریافت رسیدها: ${response.status}`);
            }
            return response.json();
        })
        .then(data => {
            console.log('✅ همه رسیدها دریافت شدند:', data);
            allReceipts = data;
            renderReceiptsTable(data);
            updateResultCount(data.length);
            showMessage(`${data.length} رسید یافت شد`, 'success');
        })
        .catch(error => {
            console.error('❌ خطا در بارگذاری رسیدها:', error);
            showMessage('خطا در بارگذاری لیست رسیدها: ' + error.message, 'error');
            updateResultCount(0);
        });
}

function clearFilter() {
    const usernameInput = document.getElementById('usernameFilter');
    if (usernameInput) {
        usernameInput.value = '';
    }
    loadAllReceipts();
}

// ============================
// رندر جدول رسیدها
// ============================

function renderReceiptsTable(receipts) {
    const tbody = document.getElementById('receiptTableBody');
    if (!tbody) return;

    if (!receipts || receipts.length === 0) {
        tbody.innerHTML = `
            <tr>
                <td colspan="13" class="empty-state">
                    <div>📭</div>
                    <div style="margin-top: 10px;">هیچ رسیدی یافت نشد</div>
                </td>
            </tr>
        `;
        return;
    }

    let html = '';
    receipts.forEach(r => {
        let statusText = r.receiptStatus || '—';
        let statusClass = 'status-unknown';

        if (r.receiptStatus === 'INIT_REGISTRATION') {
            statusText = 'در حال ثبت';
            statusClass = 'status-init';
        } else if (r.receiptStatus === 'REGISTERED') {
            statusText = 'ثبت شده';
            statusClass = 'status-registered';
        } else if (r.receiptStatus === 'CANCELED') {
            statusText = 'لغو شده';
            statusClass = 'status-canceled';
        } else if (r.receiptStatus === 'REJECTED') {
            statusText = 'رد شده';
            statusClass = 'status-canceled';
        } else if (r.receiptStatus === 'RETURNED') {
            statusText = 'عودت شده';
            statusClass = 'status-unknown';
        }

        const receiptDate = r.receiptDate ? formatDate(r.receiptDate) : '—';
        const receiptTime = r.receiptTime ? formatTime(r.receiptTime) : '—';
        const createdDate = r.createdDate ? formatDate(r.createdDate) : '—';

        html += `
            <tr>
                <td><strong>${r.receiptId}</strong></td>
                <td>${receiptDate}</td>
                <td>${receiptTime}</td>
                <td>${r.username || '—'}</td>
                <td>${r.bookTitle || '—'}</td>
                <td>${r.isbn || '—'}</td>
                <td>${r.publisherName || '—'}</td>
                <td>${r.bookSubjectTitle || '—'}</td>
                <td style="text-align: center; font-weight: 600;">${r.receiptBookCount || '0'}</td>
                <td><span class="status-badge ${statusClass}">${statusText}</span></td>
                <td>${createdDate}</td>
                <td>${r.createdBy || '—'}</td>
                <td>
                    <div style="display:flex; gap:5px; flex-wrap:wrap; justify-content:center;">
                        <button class="btn btn-warning btn-sm" data-id="${r.receiptId}" onclick="openEditModal(this)">
                            ✏️ ویرایش
                        </button>
                        <button class="btn btn-success btn-sm" data-id="${r.receiptId}" onclick="openActionModal(this, 'APPROVE')">
                            ✅ تأیید
                        </button>
                        <button class="btn btn-danger btn-sm" data-id="${r.receiptId}" onclick="openActionModal(this, 'REJECT')">
                            ❌ رد
                        </button>
                        <button class="btn btn-info btn-sm" data-id="${r.receiptId}" onclick="openActionModal(this, 'RETURN')">
                            ↩️ عودت
                        </button>
                    </div>
                </td>
            </tr>
        `;
    });

    tbody.innerHTML = html;
}

// ============================
// توابع تاریخ
// ============================

function formatDate(dateString) {
    if (!dateString) return '—';
    try {
        if (typeof dateString === 'string') {
            if (dateString.includes('T')) {
                const parts = dateString.split('T')[0].split('-');
                if (parts.length === 3) {
                    return `${parts[0]}-${parts[1]}-${parts[2]}`;
                }
            }
            const parts = dateString.split('-');
            if (parts.length === 3) {
                return dateString;
            }
        }
        const date = new Date(dateString);
        if (isNaN(date.getTime())) return '—';
        const year = date.getFullYear();
        const month = String(date.getMonth() + 1).padStart(2, '0');
        const day = String(date.getDate()).padStart(2, '0');
        return `${year}-${month}-${day}`;
    } catch (e) {
        return '—';
    }
}

function formatTime(timeString) {
    if (!timeString) return '—';
    try {
        if (typeof timeString === 'string') {
            if (timeString.includes(':')) {
                const parts = timeString.split(':');
                if (parts.length >= 2) {
                    return `${parts[0].padStart(2, '0')}:${parts[1].padStart(2, '0')}`;
                }
            }
            if (timeString.includes('T')) {
                const parts = timeString.split('T')[1].split(':');
                if (parts.length >= 2) {
                    return `${parts[0].padStart(2, '0')}:${parts[1].padStart(2, '0')}`;
                }
            }
        }
        const date = new Date(timeString);
        if (!isNaN(date.getTime())) {
            return date.toTimeString().slice(0, 5);
        }
        return '—';
    } catch (e) {
        return '—';
    }
}

// ============================
// توابع مدیریت کتاب‌ها
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
            const escapedTitle = bookTitle.replace(/'/g, "\\'").replace(/"/g, '&quot;');

            html += `
                <tr>
                    <td>${b.bookId}</td>
                    <td>${bookTitle}</td>
                    <td>${isbn}</td>
                    <td>${authors || '—'}</td>
                    <td style="text-align: center;">
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
            selectedBooks.push({ bookId: id, bookTitle: title });
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
        html = '<span style="color:#888; font-size: 13px;">هیچ کتابی انتخاب نشده</span>';
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

    if (title) title.textContent = '📝 ثبت رسید جدید';
    if (submitBtn) {
        submitBtn.textContent = '💾 ذخیره';
        submitBtn.className = 'btn btn-success';
    }
    if (form) form.reset();
    isEditMode = false;

    const receiptId = document.getElementById('receiptId');
    if (receiptId) receiptId.value = '';

    const formAction = document.getElementById('formAction');
    if (formAction) formAction.value = 'save';

    const methodInput = document.getElementById('receiptMethod');
    if (methodInput) methodInput.value = 'POST';

    if (form) form.action = '/receipt/saveReceipt';

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

            if (title) title.textContent = '✏️ ویرایش رسید';
            if (submitBtn) {
                submitBtn.textContent = '💾 به‌روزرسانی';
                submitBtn.className = 'btn btn-primary';
            }
            isEditMode = true;

            const receiptIdInput = document.getElementById('receiptId');
            if (receiptIdInput) receiptIdInput.value = receipt.receiptId || '';

            const formAction = document.getElementById('formAction');
            if (formAction) formAction.value = 'update';

            const methodInput = document.getElementById('receiptMethod');
            if (methodInput) methodInput.value = 'PUT';

            if (form) form.action = '/receipt/updateReceipt';

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
// توابع اکشن‌های تأیید / رد / عودت
// ============================

function openActionModal(button, actionType) {
    const receiptId = button.getAttribute('data-id');
    if (!receiptId) {
        showMessage('شناسه رسید یافت نشد', 'error');
        return;
    }

    document.getElementById('actionReceiptId').value = receiptId;
    document.getElementById('actionType').value = actionType;
    document.getElementById('actionDescription').value = '';

    const title = document.getElementById('actionModalTitle');
    const submitBtn = document.getElementById('actionSubmitBtn');

    if (actionType === 'APPROVE') {
        title.textContent = '✅ تأیید رسید';
        submitBtn.textContent = '✅ تأیید';
        submitBtn.className = 'btn btn-success';
    } else if (actionType === 'REJECT') {
        title.textContent = '❌ رد رسید';
        submitBtn.textContent = '❌ رد';
        submitBtn.className = 'btn btn-danger';
    } else if (actionType === 'RETURN') {
        title.textContent = '↩️ عودت رسید';
        submitBtn.textContent = '↩️ عودت';
        submitBtn.className = 'btn btn-info';
    }

    document.getElementById('actionModal').style.display = 'block';
    setTimeout(() => document.getElementById('actionDescription').focus(), 100);
}

function closeActionModal() {
    document.getElementById('actionModal').style.display = 'none';
}

function submitAction() {
    const receiptId = document.getElementById('actionReceiptId').value;
    const actionType = document.getElementById('actionType').value;
    const description = document.getElementById('actionDescription').value.trim();

    if (!description) {
        showMessage('لطفاً توضیحات را وارد کنید', 'error');
        return;
    }

    const payload = {
        receiptId: Number(receiptId),
        description: description
    };

    let url = '';
    if (actionType === 'APPROVE') {
        url = '/receipt/approve';
    } else if (actionType === 'REJECT') {
        url = '/receipt/reject';
    } else if (actionType === 'RETURN') {
        url = '/receipt/return';
    }

    fetch(url, {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(payload)
    })
        .then(response => {
            if (!response.ok) {
                return response.text().then(text => { throw new Error(text || 'خطا در انجام عملیات'); });
            }
            return response.json().catch(() => ({}));
        })
        .then(() => {
            const msg = actionType === 'APPROVE' ? 'رسید با موفقیت تأیید شد' :
                actionType === 'REJECT'  ? 'رسید با موفقیت رد شد' :
                    'رسید با موفقیت عودت داده شد';
            showMessage(msg, 'success');
            closeActionModal();

            if (currentUsername) {
                loadReceiptsByUsername();
            } else {
                loadAllReceipts();
            }
        })
        .catch(error => {
            console.error('Action error:', error);
            showMessage('خطا در انجام عملیات: ' + error.message, 'error');
        });
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
    if (form) {
        const methodInput = document.getElementById('receiptMethod');
        const formAction = document.getElementById('formAction');

        if (formAction && formAction.value === 'update') {
            const formData = new FormData(form);
            const data = {
                receiptId: formData.get('receiptId'),
                books: selectedBooks.map(b => ({ bookId: b.bookId }))
            };

            fetch('/receipt/updateReceipt', {
                method: 'PUT',
                headers: { 'Content-Type': 'application/json' },
                body: JSON.stringify(data)
            })
                .then(response => {
                    if (!response.ok) throw new Error('خطا در به‌روزرسانی رسید');
                    return response.json();
                })
                .then(() => {
                    showMessage('رسید با موفقیت به‌روزرسانی شد', 'success');
                    closeModal();
                    if (currentUsername) {
                        loadReceiptsByUsername();
                    } else {
                        loadAllReceipts();
                    }
                })
                .catch(error => {
                    showMessage('خطا در به‌روزرسانی: ' + error.message, 'error');
                });
        } else {
            form.submit();
        }
    }
    return false;
}

// ============================
// رویدادها
// ============================

document.addEventListener('DOMContentLoaded', function() {
    console.log('🚀 صفحه مدیریت رسیدها بارگذاری شد');

    loadBooks('');

    const usernameFilter = document.getElementById('usernameFilter');
    if (usernameFilter) {
        usernameFilter.addEventListener('keypress', function(e) {
            if (e.key === 'Enter') {
                loadReceiptsByUsername();
            }
        });
    }

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

    const form = document.getElementById('receiptForm');
    if (form) {
        form.addEventListener('submit', submitReceiptForm);
    }

    window.onclick = function(event) {
        if (event.target.classList.contains('modal')) {
            event.target.style.display = 'none';
        }
    };

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