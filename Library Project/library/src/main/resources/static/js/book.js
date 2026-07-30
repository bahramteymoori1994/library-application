// book.js - مدیریت صفحه کتاب‌ها

// ============================
// متغیرهای سراسری
// ============================

let allPublishers = [];
let allBookTypes = [];
let allAuthors = [];
let allLibraries = [];
let selectedAuthors = [];
let selectedLibraries = [];
let publisherSearchTimeout = null;
let bookTypeSearchTimeout = null;
let authorSearchTimeout = null;
let librarySearchTimeout = null;

// ============================
// توابع کمکی
// ============================

function getEnumDisplayName(enumValue) {
    if (!enumValue) return '';
    if (typeof enumValue === 'string') return enumValue;
    if (enumValue.displayName) return enumValue.displayName;
    if (enumValue.name) return enumValue.name;
    return String(enumValue);
}

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
// توابع مدیریت ناشران
// ============================

function loadPublishers() {
    fetch('/book/findAllPublishers')
        .then(r => {
            if (!r.ok) throw new Error('خطا در دریافت لیست ناشران');
            return r.json();
        })
        .then(data => {
            allPublishers = data;
            displayPublishers(data);
        })
        .catch(err => {
            console.error('Error loading publishers:', err);
            showMessage('خطا در بارگذاری لیست ناشران', 'error');
        });
}

function displayPublishers(publishers) {
    const tbody = document.getElementById('publisherTableBody');
    if (!tbody) return;

    let html = '';
    if (!publishers || publishers.length === 0) {
        html = `<tr><td colspan="4" class="empty-state">ناشری یافت نشد</td></tr>`;
    } else {
        publishers.forEach(p => {
            const publisherName = p.name || '';
            const publisherTypeName = p.publisherType ? (p.publisherType.name || '') : '';
            const escapedName = publisherName.replace(/'/g, "\\'");
            const escapedType = publisherTypeName.replace(/'/g, "\\'");

            html += `
                <tr>
                    <td>${p.publisherId}</td>
                    <td>${publisherName}</td>
                    <td>${publisherTypeName}</td>
                    <td>
                        <button class="btn btn-success btn-sm" onclick="selectPublisher(${p.publisherId}, '${escapedName}', '${escapedType}')">
                            انتخاب
                        </button>
                    </td>
                </tr>
            `;
        });
    }
    tbody.innerHTML = html;
}

function selectPublisher(id, name, typeName) {
    document.getElementById('publisherId').value = id;

    const infoDiv = document.getElementById('selectedPublisherInfo');
    infoDiv.classList.add('show');
    document.getElementById('selectedPublisherName').textContent = name || '';
    document.getElementById('selectedPublisherType').textContent = typeName || '';

    closePublisherSearch();
    showMessage('ناشر با موفقیت انتخاب شد', 'success');
}

function openPublisherSearch() {
    const modal = document.getElementById('publisherSearchModal');
    modal.style.display = 'block';

    if (!allPublishers.length) {
        loadPublishers();
    }

    setTimeout(() => {
        document.getElementById('publisherSearchInput').focus();
    }, 100);
}

function closePublisherSearch() {
    document.getElementById('publisherSearchModal').style.display = 'none';
}

function searchPublishers() {
    const term = document.getElementById('publisherSearchInput').value.toLowerCase().trim();
    if (term === '') {
        displayPublishers(allPublishers);
        return;
    }

    const filtered = allPublishers.filter(p => {
        const name = (p.name || '').toLowerCase();
        const typeName = p.publisherType ? (p.publisherType.name || '').toLowerCase() : '';
        return name.includes(term) || typeName.includes(term);
    });
    displayPublishers(filtered);
}

function clearPublisherSearch() {
    document.getElementById('publisherSearchInput').value = '';
    displayPublishers(allPublishers);
    document.getElementById('publisherSearchInput').focus();
}

// ============================
// توابع مدیریت نوع کتاب
// ============================

function loadBookTypes() {
    fetch('/book/findAllBookTypes')
        .then(r => {
            if (!r.ok) throw new Error('خطا در دریافت لیست نوع کتاب');
            return r.json();
        })
        .then(data => {
            allBookTypes = data;
            displayBookTypes(data);
        })
        .catch(err => {
            console.error('Error loading book types:', err);
            showMessage('خطا در بارگذاری لیست نوع کتاب', 'error');
        });
}

function displayBookTypes(bookTypes) {
    const tbody = document.getElementById('bookTypeTableBody');
    if (!tbody) return;

    let html = '';
    if (!bookTypes || bookTypes.length === 0) {
        html = `<tr><td colspan="4" class="empty-state">نوع کتابی یافت نشد</td></tr>`;
    } else {
        bookTypes.forEach(bt => {
            const subject = getEnumDisplayName(bt.bookTypeSubject);
            const language = getEnumDisplayName(bt.bookTypeLanguage);
            const escapedSubject = subject.replace(/'/g, "\\'");
            const escapedLanguage = language.replace(/'/g, "\\'");

            html += `
                <tr>
                    <td>${bt.bookTypeId}</td>
                    <td>${subject}</td>
                    <td>${language}</td>
                    <td>
                        <button class="btn btn-success btn-sm" onclick="selectBookType(${bt.bookTypeId}, '${escapedSubject}', '${escapedLanguage}')">
                            انتخاب
                        </button>
                    </td>
                </tr>
            `;
        });
    }
    tbody.innerHTML = html;
}

function selectBookType(id, subject, language) {
    document.getElementById('bookTypeId').value = id;

    const infoDiv = document.getElementById('selectedBookTypeInfo');
    infoDiv.classList.add('show');
    document.getElementById('selectedBookTypeSubject').textContent = subject || '';
    document.getElementById('selectedBookTypeLanguage').textContent = language || '';

    closeBookTypeSearch();
    showMessage('نوع کتاب با موفقیت انتخاب شد', 'success');
}

function openBookTypeSearch() {
    const modal = document.getElementById('bookTypeSearchModal');
    modal.style.display = 'block';

    if (!allBookTypes.length) {
        loadBookTypes();
    }

    setTimeout(() => {
        document.getElementById('bookTypeSearchInput').focus();
    }, 100);
}

function closeBookTypeSearch() {
    document.getElementById('bookTypeSearchModal').style.display = 'none';
}

function searchBookTypes() {
    const term = document.getElementById('bookTypeSearchInput').value.toLowerCase().trim();
    if (term === '') {
        displayBookTypes(allBookTypes);
        return;
    }

    const filtered = allBookTypes.filter(bt => {
        const subject = getEnumDisplayName(bt.bookTypeSubject).toLowerCase();
        const language = getEnumDisplayName(bt.bookTypeLanguage).toLowerCase();
        return subject.includes(term) || language.includes(term);
    });
    displayBookTypes(filtered);
}

function clearBookTypeSearch() {
    document.getElementById('bookTypeSearchInput').value = '';
    displayBookTypes(allBookTypes);
    document.getElementById('bookTypeSearchInput').focus();
}

// ============================
// توابع مدیریت نویسندگان (چندگانه)
// ============================

function loadAuthors(searchTerm) {
    const url = searchTerm ?
        `/book/searchAuthors?term=${encodeURIComponent(searchTerm)}` :
        '/book/findAllAuthors';

    fetch(url)
        .then(r => {
            if (!r.ok) throw new Error('خطا در دریافت لیست نویسندگان');
            return r.json();
        })
        .then(data => {
            allAuthors = data;
            displayAuthors(data);
        })
        .catch(err => {
            console.error('Error loading authors:', err);
            showMessage('خطا در بارگذاری لیست نویسندگان', 'error');
        });
}

function displayAuthors(authors) {
    const tbody = document.getElementById('authorTableBody');
    if (!tbody) return;

    let html = '';
    if (!authors || authors.length === 0) {
        html = `<tr><td colspan="5" class="empty-state">نویسنده‌ای یافت نشد</td></tr>`;
    } else {
        authors.forEach(a => {
            const checked = selectedAuthors.some(s => s.authorId === a.authorId) ? 'checked' : '';
            const firstName = a.person ? (a.person.firstName || '') : '';
            const lastName = a.person ? (a.person.lastName || '') : '';
            const expertise = a.authorType ? (a.authorType.name || '') : '';
            const escapedFirstName = firstName.replace(/'/g, "\\'");
            const escapedLastName = lastName.replace(/'/g, "\\'");

            html += `
                <tr>
                    <td>${a.authorId}</td>
                    <td>${firstName}</td>
                    <td>${lastName}</td>
                    <td>${expertise}</td>
                    <td>
                        <input type="checkbox" ${checked}
                               onchange="toggleAuthor(this, ${a.authorId}, '${escapedFirstName}', '${escapedLastName}')">
                    </td>
                </tr>
            `;
        });
    }
    tbody.innerHTML = html;
}

function toggleAuthor(checkbox, id, firstName, lastName) {
    if (checkbox.checked) {
        if (!selectedAuthors.some(a => a.authorId === id)) {
            selectedAuthors.push({
                authorId: id,
                firstName: firstName,
                lastName: lastName
            });
        }
    } else {
        selectedAuthors = selectedAuthors.filter(a => a.authorId !== id);
    }
    renderSelectedAuthors();
}

function searchAuthors() {
    const term = document.getElementById('authorSearchInput').value.trim();
    loadAuthors(term);
}

function clearAuthorSearch() {
    document.getElementById('authorSearchInput').value = '';
    loadAuthors('');
    document.getElementById('authorSearchInput').focus();
}

function openAuthorSearch() {
    const modal = document.getElementById('authorSearchModal');
    modal.style.display = 'block';

    if (!allAuthors.length) {
        loadAuthors('');
    }

    setTimeout(() => {
        document.getElementById('authorSearchInput').focus();
    }, 100);
}

function closeAuthorSearch() {
    document.getElementById('authorSearchModal').style.display = 'none';
}

function confirmSelectedAuthors() {
    renderSelectedAuthors();
    closeAuthorSearch();
    showMessage(`${selectedAuthors.length} نویسنده انتخاب شد`, 'success');
}

function renderSelectedAuthors() {
    const container = document.getElementById('selectedAuthorsContainer');
    if (!container) return;

    let html = '';
    if (selectedAuthors.length === 0) {
        html = '<span style="color:#888;">هیچ نویسنده‌ای انتخاب نشده</span>';
    } else {
        selectedAuthors.forEach((a, i) => {
            const displayName = (a.firstName || '') + ' ' + (a.lastName || '') || 'بدون نام';
            html += `
                <span class="role-tag">
                    ${displayName}
                    <button onclick="removeAuthor(${i})" class="remove-role" title="حذف نویسنده">×</button>
                </span>
            `;
        });
    }
    container.innerHTML = html;

    const display = document.getElementById('authorsDisplay');
    if (display) {
        display.value = selectedAuthors.length ?
            `${selectedAuthors.length} نویسنده انتخاب شده` :
            'هیچ نویسنده‌ای انتخاب نشده';
    }

    updateAuthorIdsInput();
}

function removeAuthor(index) {
    selectedAuthors.splice(index, 1);
    renderSelectedAuthors();
}

function updateAuthorIdsInput() {
    const container = document.getElementById('authorIdsContainer');
    if (!container) return;

    container.innerHTML = '';

    selectedAuthors.forEach((author, index) => {
        const input = document.createElement('input');
        input.type = 'hidden';
        input.name = `authors[${index}].authorId`;
        input.value = author.authorId;
        container.appendChild(input);

        const firstNameInput = document.createElement('input');
        firstNameInput.type = 'hidden';
        firstNameInput.name = `authors[${index}].person.firstName`;
        firstNameInput.value = author.firstName || '';
        container.appendChild(firstNameInput);

        const lastNameInput = document.createElement('input');
        lastNameInput.type = 'hidden';
        lastNameInput.name = `authors[${index}].person.lastName`;
        lastNameInput.value = author.lastName || '';
        container.appendChild(lastNameInput);
    });
}

// ============================
// توابع مدیریت کتابخانه‌ها (چندگانه)
// ============================

function loadLibraries(searchTerm) {
    const url = searchTerm ?
        `/book/searchLibraries?term=${encodeURIComponent(searchTerm)}` :
        '/book/findAllLibraries';

    fetch(url)
        .then(r => {
            if (!r.ok) throw new Error('خطا در دریافت لیست کتابخانه‌ها');
            return r.json();
        })
        .then(data => {
            allLibraries = data;
            displayLibraries(data);
        })
        .catch(err => {
            console.error('Error loading libraries:', err);
            showMessage('خطا در بارگذاری لیست کتابخانه‌ها', 'error');
        });
}

function displayLibraries(libraries) {
    const tbody = document.getElementById('libraryTableBody');
    if (!tbody) return;

    let html = '';
    if (!libraries || libraries.length === 0) {
        html = `<tr><td colspan="5" class="empty-state">کتابخانه‌ای یافت نشد</td></tr>`;
    } else {
        libraries.forEach(l => {
            const checked = selectedLibraries.some(s => s.libraryId === l.libraryId) ? 'checked' : '';
            const libraryType = l.libraryType ? (l.libraryType.name || '') : '';
            const escapedName = (l.libraryName || '').replace(/'/g, "\\'");
            const escapedCity = (l.city || '').replace(/'/g, "\\'");

            html += `
                <tr>
                    <td>${l.libraryId}</td>
                    <td>${l.libraryName || ''}</td>
                    <td>${l.city || ''}</td>
                    <td>${libraryType}</td>
                    <td>
                        <input type="checkbox" ${checked}
                               onchange="toggleLibrary(this, ${l.libraryId}, '${escapedName}', '${escapedCity}')">
                    </td>
                </tr>
            `;
        });
    }
    tbody.innerHTML = html;
}

function toggleLibrary(checkbox, id, name, city) {
    if (checkbox.checked) {
        if (!selectedLibraries.some(l => l.libraryId === id)) {
            selectedLibraries.push({
                libraryId: id,
                libraryName: name,
                libraryCity: city
            });
        }
    } else {
        selectedLibraries = selectedLibraries.filter(l => l.libraryId !== id);
    }
    renderSelectedLibraries();
}

function searchLibraries() {
    const term = document.getElementById('librarySearchInput').value.trim();
    loadLibraries(term);
}

function clearLibrarySearch() {
    document.getElementById('librarySearchInput').value = '';
    loadLibraries('');
    document.getElementById('librarySearchInput').focus();
}

function openLibrarySearch() {
    const modal = document.getElementById('librarySearchModal');
    modal.style.display = 'block';

    if (!allLibraries.length) {
        loadLibraries('');
    }

    setTimeout(() => {
        document.getElementById('librarySearchInput').focus();
    }, 100);
}

function closeLibrarySearch() {
    document.getElementById('librarySearchModal').style.display = 'none';
}

function confirmSelectedLibraries() {
    renderSelectedLibraries();
    closeLibrarySearch();
    showMessage(`${selectedLibraries.length} کتابخانه انتخاب شد`, 'success');
}

function renderSelectedLibraries() {
    const container = document.getElementById('selectedLibrariesContainer');
    if (!container) return;

    let html = '';
    if (selectedLibraries.length === 0) {
        html = '<span style="color:#888;">هیچ کتابخانه‌ای انتخاب نشده</span>';
    } else {
        selectedLibraries.forEach((l, i) => {
            const displayName = l.libraryName || 'بدون نام';
            html += `
                <span class="role-tag">
                    ${displayName} (${l.libraryCity || ''})
                    <button onclick="removeLibrary(${i})" class="remove-role" title="حذف کتابخانه">×</button>
                </span>
            `;
        });
    }
    container.innerHTML = html;

    const display = document.getElementById('librariesDisplay');
    if (display) {
        display.value = selectedLibraries.length ?
            `${selectedLibraries.length} کتابخانه انتخاب شده` :
            'هیچ کتابخانه‌ای انتخاب نشده';
    }

    updateLibraryIdsInput();
}

function removeLibrary(index) {
    selectedLibraries.splice(index, 1);
    renderSelectedLibraries();
}

function updateLibraryIdsInput() {
    const container = document.getElementById('libraryIdsContainer');
    if (!container) return;

    container.innerHTML = '';

    selectedLibraries.forEach((library, index) => {
        const input = document.createElement('input');
        input.type = 'hidden';
        input.name = `libraries[${index}].libraryId`;
        input.value = library.libraryId;
        container.appendChild(input);

        const nameInput = document.createElement('input');
        nameInput.type = 'hidden';
        nameInput.name = `libraries[${index}].libraryName`;
        nameInput.value = library.libraryName || '';
        container.appendChild(nameInput);

        const cityInput = document.createElement('input');
        cityInput.type = 'hidden';
        cityInput.name = `libraries[${index}].city`;
        cityInput.value = library.libraryCity || '';
        container.appendChild(cityInput);
    });
}

// ============================
// توابع مدیریت فرم
// ============================

function openCreateModal() {
    const modal = document.getElementById('bookModal');
    const title = document.getElementById('modalTitle');
    const form = document.getElementById('bookForm');

    title.textContent = 'ثبت کتاب جدید';
    form.reset();

    document.getElementById('bookId').value = '';

    document.getElementById('publisherId').value = '';
    document.getElementById('selectedPublisherInfo').classList.remove('show');
    document.getElementById('selectedPublisherName').textContent = '';
    document.getElementById('selectedPublisherType').textContent = '';

    document.getElementById('bookTypeId').value = '';
    document.getElementById('selectedBookTypeInfo').classList.remove('show');
    document.getElementById('selectedBookTypeSubject').textContent = '';
    document.getElementById('selectedBookTypeLanguage').textContent = '';

    selectedAuthors = [];
    renderSelectedAuthors();

    selectedLibraries = [];
    renderSelectedLibraries();

    modal.style.display = 'block';

    setTimeout(() => {
        document.getElementById('bookTitle').focus();
    }, 100);
}

function openEditModal(button) {
    const row = button.closest('tr');
    const bookId = row.querySelector('td:first-child').textContent.trim();

    showMessage('در حال بارگذاری اطلاعات کتاب...', 'info');

    fetch(`/book/findBookById/${bookId}`)
        .then(r => {
            if (!r.ok) throw new Error('خطا در دریافت اطلاعات کتاب');
            return r.json();
        })
        .then(book => {
            const modal = document.getElementById('bookModal');
            const title = document.getElementById('modalTitle');

            title.textContent = 'ویرایش کتاب';

            document.getElementById('bookId').value = book.bookId || '';
            document.getElementById('bookTitle').value = book.bookTitle || '';
            document.getElementById('isbn').value = book.isbn || '';
            document.getElementById('bookCount').value = book.bookCount || '';
            document.getElementById('publishDate').value = book.publishDate || '';

            // Publisher
            if (book.publisher) {
                document.getElementById('publisherId').value = book.publisher.publisherId || '';
                document.getElementById('selectedPublisherInfo').classList.add('show');
                document.getElementById('selectedPublisherName').textContent = book.publisher.name || '';
                const publisherType = book.publisher.publisherType ? (book.publisher.publisherType.name || '') : '';
                document.getElementById('selectedPublisherType').textContent = publisherType;
            } else {
                document.getElementById('publisherId').value = '';
                document.getElementById('selectedPublisherInfo').classList.remove('show');
            }

            // BookType
            if (book.bookType) {
                document.getElementById('bookTypeId').value = book.bookType.bookTypeId || '';
                document.getElementById('selectedBookTypeInfo').classList.add('show');
                document.getElementById('selectedBookTypeSubject').textContent = getEnumDisplayName(book.bookType.bookTypeSubject);
                document.getElementById('selectedBookTypeLanguage').textContent = getEnumDisplayName(book.bookType.bookTypeLanguage);
            } else {
                document.getElementById('bookTypeId').value = '';
                document.getElementById('selectedBookTypeInfo').classList.remove('show');
            }

            // Authors
            selectedAuthors = (book.authors || []).map(a => ({
                authorId: a.authorId,
                firstName: a.person ? (a.person.firstName || '') : '',
                lastName: a.person ? (a.person.lastName || '') : ''
            }));
            renderSelectedAuthors();

            // Libraries
            selectedLibraries = (book.libraries || []).map(l => ({
                libraryId: l.libraryId,
                libraryName: l.libraryName || '',
                libraryCity: l.city || ''
            }));
            renderSelectedLibraries();

            modal.style.display = 'block';
            showMessage('اطلاعات کتاب بارگذاری شد', 'success');
        })
        .catch(err => {
            console.error('Error loading book:', err);
            showMessage('خطا در بارگذاری اطلاعات کتاب: ' + err.message, 'error');
        });
}

function closeModal() {
    document.getElementById('bookModal').style.display = 'none';
}

// ============================
// رویدادها
// ============================

document.addEventListener('DOMContentLoaded', function() {
    loadPublishers();
    loadBookTypes();
    loadAuthors('');
    loadLibraries('');

    // جستجوی خودکار نویسندگان
    const authorSearchInput = document.getElementById('authorSearchInput');
    if (authorSearchInput) {
        authorSearchInput.addEventListener('input', function() {
            if (authorSearchTimeout) {
                clearTimeout(authorSearchTimeout);
                authorSearchTimeout = null;
            }

            const term = this.value.trim();
            if (term.length >= 2 || term.length === 0) {
                authorSearchTimeout = setTimeout(() => {
                    loadAuthors(term);
                    authorSearchTimeout = null;
                }, 400);
            }
        });
    }

    // جستجوی خودکار کتابخانه‌ها
    const librarySearchInput = document.getElementById('librarySearchInput');
    if (librarySearchInput) {
        librarySearchInput.addEventListener('input', function() {
            if (librarySearchTimeout) {
                clearTimeout(librarySearchTimeout);
                librarySearchTimeout = null;
            }

            const term = this.value.trim();
            if (term.length >= 2 || term.length === 0) {
                librarySearchTimeout = setTimeout(() => {
                    loadLibraries(term);
                    librarySearchTimeout = null;
                }, 400);
            }
        });
    }

    // جستجوی خودکار ناشران
    const publisherSearchInput = document.getElementById('publisherSearchInput');
    if (publisherSearchInput) {
        publisherSearchInput.addEventListener('input', function() {
            if (publisherSearchTimeout) {
                clearTimeout(publisherSearchTimeout);
                publisherSearchTimeout = null;
            }

            const term = this.value.trim();
            if (term.length >= 2 || term.length === 0) {
                publisherSearchTimeout = setTimeout(() => {
                    searchPublishers();
                    publisherSearchTimeout = null;
                }, 400);
            }
        });
    }

    // جستجوی خودکار نوع کتاب
    const bookTypeSearchInput = document.getElementById('bookTypeSearchInput');
    if (bookTypeSearchInput) {
        bookTypeSearchInput.addEventListener('input', function() {
            if (bookTypeSearchTimeout) {
                clearTimeout(bookTypeSearchTimeout);
                bookTypeSearchTimeout = null;
            }

            const term = this.value.trim();
            if (term.length >= 2 || term.length === 0) {
                bookTypeSearchTimeout = setTimeout(() => {
                    searchBookTypes();
                    bookTypeSearchTimeout = null;
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

    console.log('🚀 صفحه مدیریت کتاب‌ها بارگذاری شد');
});