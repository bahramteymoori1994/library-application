// welcome.js - مدیریت صفحه خوش‌آمدگویی

// ============================
// نمایش تاریخ و زمان
// ============================

function updateDateTime() {
    const now = new Date();
    const options = {
        year: 'numeric',
        month: 'long',
        day: 'numeric',
        hour: '2-digit',
        minute: '2-digit',
        second: '2-digit',
        hour12: false
    };

    const persianDateTime = now.toLocaleDateString('fa-IR', options);
    const dateTimeElement = document.getElementById('currentDateTime');

    if (dateTimeElement) {
        dateTimeElement.textContent = persianDateTime;
    }
}

// ============================
// نمایش تاریخ خوش‌آمدگویی
// ============================

function updateWelcomeDate() {
    const now = new Date();
    const options = {
        year: 'numeric',
        month: 'long',
        day: 'numeric'
    };

    const persianDate = now.toLocaleDateString('fa-IR', options);
    const dateElement = document.getElementById('welcomeDate');

    if (dateElement) {
        dateElement.textContent = `📅 ${persianDate}`;
    }
}

// ============================
// پیام خوش‌آمدگویی بر اساس زمان
// ============================

function getGreetingMessage() {
    const hour = new Date().getHours();
    let message = '';
    let emoji = '';

    if (hour >= 5 && hour < 12) {
        message = 'صبح بخیر';
        emoji = '🌅';
    } else if (hour >= 12 && hour < 17) {
        message = 'ظهر بخیر';
        emoji = '☀️';
    } else if (hour >= 17 && hour < 21) {
        message = 'عصر بخیر';
        emoji = '🌆';
    } else {
        message = 'شب بخیر';
        emoji = '🌙';
    }

    return { message, emoji };
}

function updateGreeting() {
    const greeting = getGreetingMessage();
    const greetingElement = document.getElementById('greetingMessage');

    if (greetingElement) {
        greetingElement.textContent = `${greeting.emoji} ${greeting.message}`;
    }
}

// ============================
// انیمیشن کارت‌ها
// ============================

function animateCards() {
    const cards = document.querySelectorAll('.card');

    const observer = new IntersectionObserver((entries) => {
        entries.forEach((entry, index) => {
            if (entry.isIntersecting) {
                setTimeout(() => {
                    entry.target.style.opacity = '1';
                    entry.target.style.transform = 'translateY(0)';
                }, index * 100);
            }
        });
    }, {
        threshold: 0.1,
        rootMargin: '0px 0px -50px 0px'
    });

    cards.forEach((card, index) => {
        card.style.opacity = '0';
        card.style.transform = 'translateY(30px)';
        card.style.transition = `all 0.6s ease ${index * 0.1}s`;
        observer.observe(card);
    });
}

// ============================
// جستجوی سریع در منو
// ============================

function setupQuickSearch() {
    const searchInput = document.getElementById('quickSearch');
    if (!searchInput) return;

    searchInput.addEventListener('input', function() {
        const query = this.value.toLowerCase().trim();
        const navItems = document.querySelectorAll('.nav-item');

        navItems.forEach(item => {
            const text = item.textContent.toLowerCase();
            if (text.includes(query) || query === '') {
                item.style.display = 'inline-flex';
            } else {
                item.style.display = 'none';
            }
        });
    });
}

// ============================
// آمار کارت‌ها (مثال)
// ============================

function updateCardStats() {
    // این تابع می‌تواند آمار را از سرور دریافت کند
    // و روی کارت‌ها نمایش دهد
    const stats = {
        users: 0,
        roles: 0,
        persons: 0,
        publishers: 0,
        books: 0
    };

    // دریافت آمار از سرور (نمونه)
    fetch('/api/stats')
        .then(response => response.json())
        .then(data => {
            // به‌روزرسانی کارت‌ها با آمار
            updateStatsDisplay(data);
        })
        .catch(error => {
            console.log('آمار در دسترس نیست');
        });
}

function updateStatsDisplay(data) {
    // به‌روزرسانی المان‌های آمار
    if (data.users !== undefined) {
        const userStat = document.getElementById('userStat');
        if (userStat) userStat.textContent = data.users;
    }
    // ادامه برای سایر آمارها...
}

// ============================
// ثبت رویدادها
// ============================

    document.addEventListener('DOMContentLoaded', function() {
        // به‌روزرسانی تاریخ و زمان
        updateDateTime();
        updateWelcomeDate();
        updateGreeting();

        // به‌روزرسانی هر دقیقه
        setInterval(updateDateTime, 60000);
        setInterval(updateGreeting, 60000);

        // انیمیشن کارت‌ها
        animateCards();

        // جستجوی سریع
        setupQuickSearch();

        // دریافت آمار (اختیاری)
        // updateCardStats();

        // فعال‌سازی منوی فعال
        highlightActiveMenu();

        console.log('🚀 صفحه خوش‌آمدگویی بارگذاری شد');
    });

// ============================
// هایلایت منوی فعال
// ============================

    function highlightActiveMenu() {
        const currentPath = window.location.pathname;
        const navItems = document.querySelectorAll('.nav-item');

        navItems.forEach(item => {
            const href = item.getAttribute('href');
            if (href === currentPath ||
                (href !== '/' && currentPath.startsWith(href))) {
                item.classList.add('active');
            }
        });
    }

// ============================
// نمایش اعلان
// ============================

    function showNotification(message, type = 'info') {
        // ایجاد المان اعلان
        const notification = document.createElement('div');
        notification.className = `notification notification-${type}`;
        notification.textContent = message;
        notification.style.cssText = `
        position: fixed;
        top: 20px;
        left: 50%;
        transform: translateX(-50%);
        padding: 15px 30px;
        border-radius: 8px;
        color: white;
        font-weight: 500;
        z-index: 1000;
        box-shadow: 0 4px 12px rgba(0,0,0,0.15);
        animation: slideDown 0.3s ease;
    `;

        // تنظیم رنگ بر اساس نوع
        const colors = {
            success: '#28a745',
            error: '#dc3545',
            warning: '#ffc107',
            info: '#007bff'
        };
        notification.style.backgroundColor = colors[type] || colors.info;

        // اضافه کردن به صفحه
        document.body.appendChild(notification);

        // حذف بعد از 5 ثانیه
        setTimeout(() => {
            notification.style.opacity = '0';
            notification.style.transition = 'opacity 0.5s ease';
            setTimeout(() => {
                notification.remove();
            }, 500);
        }, 5000);
    }

// ============================
    // به‌روزرسانی خودکار (اختیاری)
// ============================

    function autoRefresh() {
        // به‌روزرسانی خودکار صفحه هر 5 دقیقه (اختیاری)
        // setInterval(() => {
        //     location.reload();
        // }, 300000);
    }

// ============================
    // مدیریت خطاها
// ============================

    window.addEventListener('error', function(event) {
        console.error('خطا در صفحه:', event.message);
        // نمایش اعلان خطا
        // showNotification('خطایی رخ داد، لطفاً دوباره تلاش کنید', 'error');
    });

// ============================
// عملکرد دکمه‌ها
// ============================

// اضافه کردن functionality به دکمه‌ها
    document.addEventListener('DOMContentLoaded', function() {
        // دکمه‌های کارت‌ها
        const cardButtons = document.querySelectorAll('.card .btn');
        cardButtons.forEach(button => {
            button.addEventListener('click', function(event) {
                // می‌توانید track کلیک کنید
                console.log('Navigating to:', this.getAttribute('href'));
            });
        });
    });