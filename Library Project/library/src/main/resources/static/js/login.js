document.addEventListener('DOMContentLoaded', function() {
    const form = document.getElementById('loginForm');
    const username = document.getElementById('username');
    const password = document.getElementById('password');
    const usernameError = document.getElementById('usernameError');
    const passwordError = document.getElementById('passwordError');
    const btnLogin = document.querySelector('.btn-login');

    // اعتبارسنجی لحظه‌ای
    username.addEventListener('input', function() {
        if (this.value.trim()) {
            usernameError.classList.remove('show');
            this.classList.remove('error');
        }
    });

    password.addEventListener('input', function() {
        if (this.value.length >= 6) {
            passwordError.classList.remove('show');
            this.classList.remove('error');
        }
    });

    // ارسال فرم
    form.addEventListener('submit', function(event) {
        event.preventDefault();

        // ریست خطاها
        usernameError.classList.remove('show');
        passwordError.classList.remove('show');
        username.classList.remove('error');
        password.classList.remove('error');

        let isValid = true;

        // اعتبارسنجی نام کاربری
        if (!username.value.trim()) {
            usernameError.classList.add('show');
            username.classList.add('error');
            isValid = false;
        }

        // اعتبارسنجی رمز عبور
        if (password.value.length < 6) {
            passwordError.classList.add('show');
            password.classList.add('error');
            isValid = false;
        }

        if (isValid) {
            // شبیه‌سازی ورود
            btnLogin.textContent = 'در حال ورود...';
            btnLogin.disabled = true;

            setTimeout(() => {
                alert('✅ ورود موفقیت‌آمیز!\nخوش آمدید ' + username.value);
                btnLogin.textContent = 'ورود';
                btnLogin.disabled = false;
            }, 1500);
        }
    });
});