// book.js - مدیریت صفحه کتاب‌ها

// ============================
// متغیرهای سراسری
// ============================

let allPublishers = [];
let allBookSubjects = [];
let allAuthors = [];
let allTranslators = [];
let allLibraries = [];
let selectedAuthors = [];
let selectedTranslators = [];
let selectedLibraries = [];
let publisherSearchTimeout = null;
let bookSubjectSearchTimeout = null;
let authorSearchTimeout = null;
let translatorSearchTimeout = null;
let librarySearchTimeout = null;
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
// توابع مدیریت نمایش مترجمان
// ============================

function toggleTranslatorSection() {
    const translateStatus = document.getElementById('translateStatus').value;
    const translatorSection = document.getElementById('translatorSection');

    if (translateStatus === 'TRANSLATED') {
        translatorSection.style.display = 'block';
        if (selectedTranslators.length === 0) {
            document.getElementById('translatorsDisplay').value = 'هیچ مترجمی انتخاب نشده';
        }
    } else {
        translatorSection.style.display = 'none';
        selectedTranslators = [];
        renderSelectedTranslators();
    }
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
// توابع مدیریت موضوع کتاب
// ============================

function loadBookSubjects() {
    fetch('/book/findAllBookSubjects')
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
        html = `<tr><td colspan="4" class="empty-state">موضوعی یافت نشد</td></tr>`;
    } else {
        subjects.forEach(s => {
            const subjectTitle = s.subjectTitle || '';
            const parentTitle = s.bookSubject ? (s.bookSubject.subjectTitle || '') : '—';
            const escapedTitle = subjectTitle.replace(/'/g, "\\'");
            const escapedParent = parentTitle.replace(/'/g, "\\'");

            html += `
                <tr>
                    <td>${s.bookSubjectId}</td>
                    <td>${subjectTitle}</td>
                    <td>${parentTitle}</td>
                    <td>
                        <button class="btn btn-success btn-sm" onclick="selectBookSubject(${s.bookSubjectId}, '${escapedTitle}', '${escapedParent}')">
                            انتخاب
                        </button>
                    </td>
                </tr>
            `;
        });
    }
    tbody.innerHTML = html;
}

function selectBookSubject(id, title, parentTitle) {
    document.getElementById('bookSubjectId').value = id;

    const infoDiv = document.getElementById('selectedBookSubjectInfo');
    infoDiv.classList.add('show');
    document.getElementById('selectedBookSubjectTitle').textContent = title || '';
    document.getElementById('selectedBookSubjectParent').textContent = parentTitle || '—';

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

    const filtered = allBookSubjects.filter(s => {
        const title = (s.subjectTitle || '').toLowerCase();
        const parent = s.bookSubject ? (s.bookSubject.subjectTitle || '').toLowerCase() : '';
        return title.includes(term) || parent.includes(term);
    });
    displayBookSubjects(filtered);
}

function clearBookSubjectSearch() {
    document.getElementById('bookSubjectSearchInput').value = '';
    displayBookSubjects(allBookSubjects);
    document.getElementById('bookSubjectSearchInput').focus();
}

// ============================
// توابع مدیریت نویسندگان (چندگانه)
// ============================

function loadAuthors(searchTerm) {
    const url = searchTerm ?
        `/book/findAllAuthors?term=${encodeURIComponent(searchTerm)}` :
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
            const firstName = a.firstName || '';
            const lastName = a.lastName || '';
            const expertise = a.authorType ? (a.authorType.role || '') : '';
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
    });
}

// ============================
// توابع مدیریت مترجمان (چندگانه)
// ============================

function loadTranslators(searchTerm) {
    const url = searchTerm ?
        `/book/findAllTranslators?term=${encodeURIComponent(searchTerm)}` :
        '/book/findAllTranslators';

    fetch(url)
        .then(r => {
            if (!r.ok) throw new Error('خطا در دریافت لیست مترجمان');
            return r.json();
        })
        .then(data => {
            allTranslators = data;
            displayTranslators(data);
        })
        .catch(err => {
            console.error('Error loading translators:', err);
            showMessage('خطا در بارگذاری لیست مترجمان', 'error');
        });
}

function displayTranslators(translators) {
    const tbody = document.getElementById('translatorTableBody');
    if (!tbody) return;

    let html = '';
    if (!translators || translators.length === 0) {
        html = `<tr><td colspan="5" class="empty-state">مترجمی یافت نشد</td></tr>`;
    } else {
        translators.forEach(t => {
            const checked = selectedTranslators.some(s => s.translatorId === t.translatorId) ? 'checked' : '';
            const firstName = t.firstName || '';
            const lastName = t.lastName || '';
            const languages = t.translateLanguages || '';
            const escapedFirstName = firstName.replace(/'/g, "\\'");
            const escapedLastName = lastName.replace(/'/g, "\\'");

            html += `
                <tr>
                    <td>${t.translatorId}</td>
                    <td>${firstName}</td>
                    <td>${lastName}</td>
                    <td>${languages}</td>
                    <td>
                        <input type="checkbox" ${checked}
                               onchange="toggleTranslator(this, ${t.translatorId}, '${escapedFirstName}', '${escapedLastName}')">
                    </td>
                </tr>
            `;
        });
    }
    tbody.innerHTML = html;
}

function toggleTranslator(checkbox, id, firstName, lastName) {
    if (checkbox.checked) {
        if (!selectedTranslators.some(t => t.translatorId === id)) {
            selectedTranslators.push({
                translatorId: id,
                firstName: firstName,
                lastName: lastName
            });
        }
    } else {
        selectedTranslators = selectedTranslators.filter(t => t.translatorId !== id);
    }
    renderSelectedTranslators();
}

function searchTranslators() {
    const term = document.getElementById('translatorSearchInput').value.trim();
    loadTranslators(term);
}

function clearTranslatorSearch() {
    document.getElementById('translatorSearchInput').value = '';
    loadTranslators('');
    document.getElementById('translatorSearchInput').focus();
}

function openTranslatorSearch() {
    const modal = document.getElementById('translatorSearchModal');
    modal.style.display = 'block';

    if (!allTranslators.length) {
        loadTranslators('');
    }

    setTimeout(() => {
        document.getElementById('translatorSearchInput').focus();
    }, 100);
}

function closeTranslatorSearch() {
    document.getElementById('translatorSearchModal').style.display = 'none';
}

function confirmSelectedTranslators() {
    renderSelectedTranslators();
    closeTranslatorSearch();
    const count = selectedTranslators.length;
    showMessage(`${count} مترجم انتخاب شد`, 'success');

    const translatorSection = document.getElementById('translatorSection');
    if (translatorSection && translatorSection.style.display === 'none' && count > 0) {
        translatorSection.style.display = 'block';
    }
}

function renderSelectedTranslators() {
    const container = document.getElementById('selectedTranslatorsContainer');
    if (!container) return;

    let html = '';
    if (selectedTranslators.length === 0) {
        html = '<span style="color:#888;">هیچ مترجمی انتخاب نشده</span>';
    } else {
        selectedTranslators.forEach((t, i) => {
            const displayName = (t.firstName || '') + ' ' + (t.lastName || '') || 'بدون نام';
            html += `
                <span class="role-tag">
                    ${displayName}
                    <button onclick="removeTranslator(${i})" class="remove-role" title="حذف مترجم">×</button>
                </span>
            `;
        });
    }
    container.innerHTML = html;

    const display = document.getElementById('translatorsDisplay');
    if (display) {
        display.value = selectedTranslators.length ?
            `${selectedTranslators.length} مترجم انتخاب شده` :
            'هیچ مترجمی انتخاب نشده';
    }

    updateTranslatorIdsInput();
}

function removeTranslator(index) {
    selectedTranslators.splice(index, 1);
    renderSelectedTranslators();
}

function updateTranslatorIdsInput() {
    const container = document.getElementById('translatorIdsContainer');
    if (!container) return;

    container.innerHTML = '';

    selectedTranslators.forEach((translator, index) => {
        const input = document.createElement('input');
        input.type = 'hidden';
        input.name = `translators[${index}].translatorId`;
        input.value = translator.translatorId;
        container.appendChild(input);
    });
}

// ============================
// توابع مدیریت کتابخانه‌ها (چندگانه)
// ============================

function loadLibraries(searchTerm) {
    const url = searchTerm ?
        `/book/findAllLibraries?term=${encodeURIComponent(searchTerm)}` :
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
            const libraryName = l.libraryName || '';
            const city = l.city || '';
            const ownership = l.libraryOwnership || '';
            const escapedName = libraryName.replace(/'/g, "\\'");
            const escapedCity = city.replace(/'/g, "\\'");

            html += `
                <tr>
                    <td>${l.libraryId}</td>
                    <td>${libraryName}</td>
                    <td>${city}</td>
                    <td>${ownership}</td>
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
    });
}

// ============================
// توابع مدیریت فرم
// ============================

function openCreateModal() {
    const modal = document.getElementById('bookModal');
    const title = document.getElementById('modalTitle');
    const form = document.getElementById('bookForm');
    const submitBtn = document.getElementById('submitBtn');

    title.textContent = 'ثبت کتاب جدید';
    submitBtn.textContent = 'ذخیره';
    submitBtn.className = 'btn btn-success';
    form.reset();
    isEditMode = false;

    document.getElementById('bookId').value = '';
    document.getElementById('formAction').value = 'save';
    form.action = '/book/saveBook';

    document.getElementById('publisherId').value = '';
    document.getElementById('selectedPublisherInfo').classList.remove('show');

    document.getElementById('bookSubjectId').value = '';
    document.getElementById('selectedBookSubjectInfo').classList.remove('show');

    document.getElementById('bookCount').value = 1;

    selectedAuthors = [];
    renderSelectedAuthors();

    selectedTranslators = [];
    renderSelectedTranslators();

    selectedLibraries = [];
    renderSelectedLibraries();

    const translatorSection = document.getElementById('translatorSection');
    if (translatorSection) {
        translatorSection.style.display = 'none';
    }

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
            const submitBtn = document.getElementById('submitBtn');
            const form = document.getElementById('bookForm');

            title.textContent = 'ویرایش کتاب';
            submitBtn.textContent = 'به‌روزرسانی';
            submitBtn.className = 'btn btn-primary';
            isEditMode = true;

            document.getElementById('bookId').value = book.bookId || '';
            document.getElementById('formAction').value = 'update';
            form.action = '/book/updateBook';

            document.getElementById('bookTitle').value = book.bookTitle || '';
            document.getElementById('isbn').value = book.isbn || '';
            document.getElementById('description').value = book.description || '';
            document.getElementById('publishYear').value = book.publishYear || '';
            document.getElementById('publishNumber').value = book.publishNumber || '';
            document.getElementById('pageCount').value = book.pageCount || '';
            document.getElementById('bookCount').value = book.bookCount || 0;

            document.getElementById('translateStatus').value = book.translateStatus || '';

            const translatorSection = document.getElementById('translatorSection');
            if (book.translateStatus === 'TRANSLATED') {
                translatorSection.style.display = 'block';
            } else {
                translatorSection.style.display = 'none';
            }

            document.getElementById('historicalPeriodLevel').value = book.historicalPeriodLevel || '';

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

            if (book.bookSubject) {
                document.getElementById('bookSubjectId').value = book.bookSubject.bookSubjectId || '';
                document.getElementById('selectedBookSubjectInfo').classList.add('show');
                document.getElementById('selectedBookSubjectTitle').textContent = book.bookSubject.subjectTitle || '';
                const parentTitle = book.bookSubject.bookSubject ? (book.bookSubject.bookSubject.subjectTitle || '') : '—';
                document.getElementById('selectedBookSubjectParent').textContent = parentTitle;
            } else {
                document.getElementById('bookSubjectId').value = '';
                document.getElementById('selectedBookSubjectInfo').classList.remove('show');
            }

            selectedAuthors = (book.authors || []).map(a => ({
                authorId: a.authorId,
                firstName: a.firstName || '',
                lastName: a.lastName || ''
            }));
            renderSelectedAuthors();

            if (book.translateStatus === 'TRANSLATED') {
                selectedTranslators = (book.translators || []).map(t => ({
                    translatorId: t.translatorId,
                    firstName: t.firstName || '',
                    lastName: t.lastName || ''
                }));
            } else {
                selectedTranslators = [];
            }
            renderSelectedTranslators();

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
// ارسال فرم
// ============================

function submitBookForm(event) {
    if (event) {
        event.preventDefault();
    }

    const bookTitle = document.getElementById('bookTitle').value.trim();
    const isbn = document.getElementById('isbn').value.trim();
    const publisherId = document.getElementById('publisherId').value.trim();
    const bookSubjectId = document.getElementById('bookSubjectId').value.trim();
    const translateStatus = document.getElementById('translateStatus').value;
    const bookCount = document.getElementById('bookCount').value;

    if (!bookTitle) {
        showMessage('لطفاً عنوان کتاب را وارد کنید', 'error');
        document.getElementById('bookTitle').focus();
        return false;
    }

    if (!isbn) {
        showMessage('لطفاً شابک کتاب را وارد کنید', 'error');
        document.getElementById('isbn').focus();
        return false;
    }

    if (!publisherId) {
        showMessage('لطفاً ناشر را انتخاب کنید', 'error');
        return false;
    }

    if (!bookSubjectId) {
        showMessage('لطفاً موضوع کتاب را انتخاب کنید', 'error');
        return false;
    }

    if (!translateStatus) {
        showMessage('لطفاً وضعیت ترجمه را انتخاب کنید', 'error');
        document.getElementById('translateStatus').focus();
        return false;
    }

    if (bookCount === '' || bookCount === null || bookCount < 0) {
        showMessage('لطفاً تعداد کتاب را وارد کنید', 'error');
        document.getElementById('bookCount').focus();
        return false;
    }

    if (bookCount > 255) {
        showMessage('تعداد کتاب نباید بیشتر از ۲۵۵ باشد', 'error');
        document.getElementById('bookCount').focus();
        return false;
    }

    if (translateStatus === 'TRANSLATED' && selectedTranslators.length === 0) {
        showMessage('لطفاً حداقل یک مترجم انتخاب کنید', 'error');
        return false;
    }

    document.getElementById('bookForm').submit();
}

// ============================
// رویدادها
// ============================

document.addEventListener('DOMContentLoaded', function() {
    loadPublishers();
    loadBookSubjects();
    loadAuthors('');
    loadTranslators('');
    loadLibraries('');

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

    const translatorSearchInput = document.getElementById('translatorSearchInput');
    if (translatorSearchInput) {
        translatorSearchInput.addEventListener('input', function() {
            if (translatorSearchTimeout) {
                clearTimeout(translatorSearchTimeout);
                translatorSearchTimeout = null;
            }

            const term = this.value.trim();
            if (term.length >= 2 || term.length === 0) {
                translatorSearchTimeout = setTimeout(() => {
                    loadTranslators(term);
                    translatorSearchTimeout = null;
                }, 400);
            }
        });
    }

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

    const bookSubjectSearchInput = document.getElementById('bookSubjectSearchInput');
    if (bookSubjectSearchInput) {
        bookSubjectSearchInput.addEventListener('input', function() {
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

    const translateStatus = document.getElementById('translateStatus');
    if (translateStatus) {
        translateStatus.addEventListener('change', toggleTranslatorSection);
    }

    const form = document.getElementById('bookForm');
    if (form) {
        form.addEventListener('submit', submitBookForm);
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

    console.log('🚀 صفحه مدیریت کتاب‌ها بارگذاری شد');
});