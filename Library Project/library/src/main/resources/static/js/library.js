// library.js - مدیریت صفحه کتابخانه‌ها

let allLibraryTypes = [];
let libraryTypeSearchTimeout = null;

// =============================================
// ================ نوع کتابخانه ===============
// =============================================

function loadLibraryTypes() {
    fetch('/library/findAllLibraryTypes')
        .then(r => r.json())
        .then(data => {
            allLibraryTypes = data;
            displayLibraryTypes(data);
            console.log('📚 لیست انواع کتابخانه بارگذاری شد:', data);
        })
        .catch(err => {
            console.error('Error loading library types:', err);
            showMessage('خطا در بارگذاری انواع کتابخانه', 'error');
        });
}

function getLibraryTypeDisplayName(type) {
    // ترکیب نوع خدمات و نوع مالکیت برای نمایش
    let parts = [];
    if (type.libraryTypeService) {
        parts.push(type.libraryTypeService);
    }
    if (type.libraryTypeOwnership) {
        parts.push(type.libraryTypeOwnership);
    }
    if (type.libraryTypeAccessibility) {
        parts.push(type.libraryTypeAccessibility);
    }
    if (type.libraryTypeScope) {
        parts.push(type.libraryTypeScope);
    }
    if (type.libraryTypeSize) {
        parts.push(type.libraryTypeSize);
    }
    if (type.historicalPeriodLevel) {
        parts.push(type.historicalPeriodLevel);
    }

    // اگر هیچ کدام نبود، از شناسه استفاده کن
    if (parts.length === 0) {
        return type.title || type.libraryTypeTitle || 'نوع کتابخانه (ID: ' + (type.libraryTypeId || type.id) + ')';
    }

    return parts.join(' | ');
}

function displayLibraryTypes(types) {
    const tbody = document.getElementById('libraryTypeTableBody');
    if (!tbody) return;

    let html = '';
    if (!types || types.length === 0) {
        html = `<tr><td colspan="4" class="empty-state">موردی یافت نشد</td></tr>`;
    } else {
        types.forEach(t => {
            const id = t.libraryTypeId || t.id || '';
            const displayName = getLibraryTypeDisplayName(t);
            const createdBy = t.createdBy || 'نامشخص';
            html += `
                <tr onclick="selectLibraryType('${id}','${displayName.replace(/'/g, "\\'")}')">
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

function selectLibraryType(id, displayName) {
    console.log('✅ انتخاب نوع کتابخانه:', id, displayName);
    document.getElementById('libraryTypeId').value = id;
    const infoDiv = document.getElementById('selectedLibraryTypeInfo');
    infoDiv.classList.add('show');
    document.getElementById('selectedLibraryTypeTitle').textContent = displayName;
    closeLibraryTypeSearch();
    showMessage('نوع کتابخانه با موفقیت انتخاب شد', 'success');
}

function openLibraryTypeSearch() {
    const modal = document.getElementById('libraryTypeSearchModal');
    modal.style.display = 'block';

    if (!allLibraryTypes.length) {
        loadLibraryTypes();
    } else {
        displayLibraryTypes(allLibraryTypes);
    }

    setTimeout(() => {
        document.getElementById('libraryTypeSearchInput').focus();
    }, 100);
}

function closeLibraryTypeSearch() {
    document.getElementById('libraryTypeSearchModal').style.display = 'none';
}

function searchLibraryTypes() {
    const term = document.getElementById('libraryTypeSearchInput').value.toLowerCase().trim();
    if (term === '') {
        displayLibraryTypes(allLibraryTypes);
        return;
    }

    const filtered = allLibraryTypes.filter(t => {
        const displayName = getLibraryTypeDisplayName(t).toLowerCase();
        return displayName.includes(term);
    });
    displayLibraryTypes(filtered);
}

function clearLibraryTypeSearch() {
    document.getElementById('libraryTypeSearchInput').value = '';
    displayLibraryTypes(allLibraryTypes);
    document.getElementById('libraryTypeSearchInput').focus();
}

// =============================================
// ================ ویرایش =====================
// =============================================

function openCreateModal() {
    document.getElementById('modalTitle').textContent = 'ثبت کتابخانه جدید';
    document.getElementById('libraryForm').reset();
    document.getElementById('selectedLibraryTypeInfo').classList.remove('show');
    document.getElementById('libraryId').value = '';
    document.getElementById('libraryTypeId').value = '';
    document.getElementById('selectedLibraryTypeTitle').textContent = 'هیچ نوعی انتخاب نشده';
    document.getElementById('libraryModal').style.display = 'block';
}

function openEditModal(button) {
    // پیدا کردن ردیف و شناسه کتابخانه
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

    const libraryId = cells[0].textContent.trim();
    console.log('🔍 شناسه کتابخانه برای ویرایش:', libraryId);

    if (!libraryId || libraryId === '') {
        showMessage('شناسه کتابخانه معتبر نیست', 'error');
        return;
    }

    showMessage('در حال بارگذاری اطلاعات کتابخانه...', 'info');

    // اگر لیست انواع کتابخانه خالی است، بارگذاری کن
    if (allLibraryTypes.length === 0) {
        loadLibraryTypes();
    }

    fetch(`/library/findLibraryById/${libraryId}`)
        .then(response => {
            console.log('📡 وضعیت پاسخ:', response.status);
            if (!response.ok) {
                throw new Error(`خطای سرور: ${response.status}`);
            }
            return response.json();
        })
        .then(library => {
            console.log('✅ داده دریافت شده:', library);

            if (!library || Object.keys(library).length === 0) {
                throw new Error('داده‌ای دریافت نشد');
            }

            document.getElementById('modalTitle').textContent = 'ویرایش کتابخانه';

            // تنظیم شناسه کتابخانه
            const libraryIdField = document.getElementById('libraryId');
            if (libraryIdField) {
                libraryIdField.value = library.libraryId || library.id || '';
            }

            // ========== تنظیم اطلاعات کتابخانه ==========
            document.getElementById('libraryName').value = library.libraryName || '';
            document.getElementById('libraryCode').value = library.libararyCode || library.libraryCode || '';
            document.getElementById('city').value = library.libraryCity || library.city || '';
            document.getElementById('phone').value = library.libraryPhone || library.phone || '';
            document.getElementById('address').value = library.libraryAddress || library.address || '';

            // ========== نمایش نوع کتابخانه ==========
            // دریافت libraryTypeId از پاسخ
            let typeId = library.libraryTypeId ||
                library.libraryType?.libraryTypeId ||
                library.libraryType?.id ||
                '';

            console.log('🔍 libraryTypeId دریافت شده:', typeId);

            // تنظیم مقدار در فیلد hidden
            if (typeId) {
                document.getElementById('libraryTypeId').value = typeId;

                // پیدا کردن نوع کتابخانه در لیست allLibraryTypes
                let type = null;

                // جستجو در allLibraryTypes
                if (allLibraryTypes.length > 0) {
                    type = allLibraryTypes.find(t => {
                        const tId = t.libraryTypeId || t.id;
                        return String(tId) === String(typeId);
                    });
                }

                if (type) {
                    console.log('✅ نوع کتابخانه پیدا شد:', type);
                    const displayName = getLibraryTypeDisplayName(type);

                    // نمایش اطلاعات انتخاب شده
                    const infoDiv = document.getElementById('selectedLibraryTypeInfo');
                    infoDiv.classList.add('show');
                    document.getElementById('selectedLibraryTypeTitle').textContent = displayName;
                } else {
                    console.warn('⚠️ نوع کتابخانه با ID', typeId, 'در لیست یافت نشد');
                    // تلاش برای بارگذاری مجدد
                    fetch('/library/findAllLibraryTypes')
                        .then(r => r.json())
                        .then(data => {
                            allLibraryTypes = data;
                            displayLibraryTypes(data);

                            // دوباره جستجو
                            const foundType = allLibraryTypes.find(t => {
                                const tId = t.libraryTypeId || t.id;
                                return String(tId) === String(typeId);
                            });

                            if (foundType) {
                                const displayName = getLibraryTypeDisplayName(foundType);
                                const infoDiv = document.getElementById('selectedLibraryTypeInfo');
                                infoDiv.classList.add('show');
                                document.getElementById('selectedLibraryTypeTitle').textContent = displayName;
                            } else {
                                // نمایش ID به عنوان fallback
                                const infoDiv = document.getElementById('selectedLibraryTypeInfo');
                                infoDiv.classList.add('show');
                                document.getElementById('selectedLibraryTypeTitle').textContent = 'نوع کتابخانه (ID: ' + typeId + ')';
                            }
                        })
                        .catch(err => {
                            console.error('Error reloading library types:', err);
                            // نمایش ID به عنوان fallback
                            const infoDiv = document.getElementById('selectedLibraryTypeInfo');
                            infoDiv.classList.add('show');
                            document.getElementById('selectedLibraryTypeTitle').textContent = 'نوع کتابخانه (ID: ' + typeId + ')';
                        });
                }
            } else {
                console.warn('⚠️ libraryTypeId در پاسخ وجود ندارد');
                document.getElementById('selectedLibraryTypeInfo').classList.remove('show');
                document.getElementById('selectedLibraryTypeTitle').textContent = 'هیچ نوعی انتخاب نشده';
            }

            document.getElementById('libraryModal').style.display = 'block';

            showMessage('اطلاعات کتابخانه با موفقیت بارگذاری شد', 'success');
        })
        .catch(error => {
            console.error('❌ خطا در ویرایش:', error);
            showMessage('خطا در بارگذاری اطلاعات: ' + error.message, 'error');
        });
}

function closeModal() {
    document.getElementById('libraryModal').style.display = 'none';
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
    loadLibraryTypes();

    // جستجوی خودکار برای نوع کتابخانه
    const libraryTypeInput = document.getElementById('libraryTypeSearchInput');
    if (libraryTypeInput) {
        libraryTypeInput.addEventListener('input', function() {
            if (libraryTypeSearchTimeout) clearTimeout(libraryTypeSearchTimeout);
            const term = this.value.trim();
            if (term.length >= 2 || term.length === 0) {
                libraryTypeSearchTimeout = setTimeout(searchLibraryTypes, 400);
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

    console.log('🚀 صفحه مدیریت کتابخانه‌ها بارگذاری شد');
});