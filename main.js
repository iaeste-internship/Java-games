// Wait until the HTML document is fully loaded
document.addEventListener('DOMContentLoaded', () => {
    // 1) Exit early if this page has no cookie banner
    const banner = document.getElementById('cookie-banner');
    if (!banner) return;

    // 2) Safely grab references (they may be missing on some pages)
    const $ = (id) => document.getElementById(id);
    const acceptAll = $('accept-all');
    const rejectAll = $('reject-all');
    const customize = $('customize');
    const closeBtn  = $('cookie-close');

    // 3) Do not re-open the banner if consent is already stored
    const saved = localStorage.getItem('cookieConsent');
    if (!saved) setTimeout(() => banner.classList.add('show'), 200);

    // 4) Store user's choice and hide the banner
    function setConsent(value) {
        localStorage.setItem('cookieConsent', value);
        banner.classList.remove('show');
        console.log('Cookie consent:', value);
    }

    // 5) Attach listeners (optional chaining prevents errors if a button is missing)
    acceptAll?.addEventListener('click', () => setConsent('all'));
    rejectAll?.addEventListener('click', () => setConsent('none'));
    customize?.addEventListener('click', () => {
        setConsent('custom');
        alert('Here you would open the custom cookie settings panel.');
    });
    closeBtn?.addEventListener('click', () => banner.classList.remove('show'));
});

// 6) (Optional) Stub legacy function to avoid "translateBanner is not defined" errors
window.translateBanner ??= () => {};


// Object containing translations for different languages
const i18n = {
    es: {

        title:     "Aviso sobre cookies",
        desc:      "Las cookies se utilizan para personalizar el contenido y los anuncios, proporcionar contenido para redes sociales y analizar el tráfico. Puedes aceptar todas las cookies seleccionando \"Permitir todo\" o ajustar tu configuración seleccionando \"Personalizar la configuración de cookies\". Al visitar cualquier sitio web, este puede almacenar o recibir información en tu navegador, principalmente en forma de cookies. Esta información puede ser sobre ti, tus preferencias o tu dispositivo, y se utiliza principalmente para que el sitio funcione correctamente. Por lo general, la información no te identifica directamente, pero puede ofrecerte una experiencia web más personalizada. Respetamos tu derecho a la privacidad, por lo que puedes optar por no permitir ciertas cookies. Haz clic en los diferentes encabezados de categoría para obtener más información y cambiar nuestra configuración predeterminada. Haz clic aquí para ver la política de cookies.",
        btnAccept: "Permitir todo",
        btnReject: "Rechazar todo",
        btnCustom: "Personalizar la configuración de cookies",
        researchTitle: "Nuestros proyectos y próximos pasos",
        gameTitleSection: "Los juegos hechos por nuestros alumnos",
        gameTitle:"UNO:",
        gameDescriptionUno: "Este simulador del juego de cartas Uno es una aplicación Java/Swing donde juegas contra robots informáticos, robando y jugando cartas para igualar color, número o símbolo.\n" +
            "Cuenta con un sistema de inicio de sesión con registro de usuario, una tabla de clasificación con victorias, derrotas y puntuación total, además de sesiones de juego guardadas.\n" +
            "El juego incluye robar y descartar cartas de los montones, manejo de cartas especiales (Robar Dos, Invertir, Saltar, Comodín, Comodín Roba Cuatro), declaraciones de Uno y penalizaciones por no decir \"Uno\".\n" +
            "Todas las acciones y eventos del juego se registran en archivos de texto, y las estadísticas del jugador (ratio de victorias/derrotas, puntuación media) se almacenan y se muestran a través de la interfaz gráfica de Swing.",
        gameDescriptionKnowledge:"Knowledge Siege es un juego educativo Java/Swing en el que mueves un avatar para atrapar \"ShotBoxes\" informativos y evitar los dañinos cuadros de preguntas.\n" +
            "Te enfrentarás a los \"Guardianes del Conocimiento\" (Líderes de Sección, Ayudantes de Profesor y Profesores) en tres niveles progresivos, ganando puntos y gestionando tu salud.\n" +
            "La jugabilidad combina movimiento horizontal, disparos cronometrados a cajas desde arriba y detección de colisiones mediante temporizadores Swing.\n" +
            "El juego carga contenido de archivos externos, registra eventos y refuerza principios de programación orientada a objetos (POO) como la herencia, el polimorfismo y la encapsulación.",

        research: "Investigación",
        games:"Juegos",
        home:"Página de la universidad",
        loginTitle: "Iniciar sesión para jugar a UNO",
        username: "Usuario",
        password: "Contraseña",
        loginButton: "Entrar",
        loginError: "Usuario o contraseña incorrectos",
        play:"Juégame!",
        firstgame:"Primer juego:",
        secondgame:"Segundo juego:",
        goToRegister: "Crear cuenta",
        goToLogin: "¿Ya tienes cuenta? Inicia sesión",
        registerTitle: "Crear una cuenta",
        registerButton: "Registrarme",
        passwordRepeat: "Repite la contraseña",
        passwordRepeatPh: "Repite tu contraseña",
        registerMissing: "Faltan usuario o contraseña",
        registerPwdMismatch: "Las contraseñas no coinciden",
        registerUserExists: "Ese usuario ya existe",
        registerWriteError: "No se pudo guardar el usuario",
        registerErrorGeneric: "No se pudo crear la cuenta",


    },
    en: {
        title:     "Cookie Notice",
        desc:      "We use cookies to personalize content and ads, provide social media features, and analyze traffic. You can accept all cookies by selecting “Accept all” or adjust your settings by choosing “Customize cookie settings.” When you visit any website, it may store or retrieve information in your browser in the form of cookies. This information may concern you, your preferences, or your device, and is primarily used so that the site works correctly. Generally, the information doesn’t directly identify you but can offer you a more personalized web experience. We respect your right to privacy, so you may opt out of certain cookies. Click on the different category headings for more information and to change our default settings. Click here to view the cookie policy.",
        btnAccept: "Accept all",
        btnReject: "Reject all",
        btnCustom: "Customize cookie settings",
        researchTitle:" Our research and our goals",
        gameTitleSection:"The games that our students have done",
        gameTitle:"UNO:",
        gameDescriptionUno:"This Uno Card Game simulator is a Java/Swing application where you play against computer “bots,” drawing and playing cards to match color, number, or symbol.\n" +
            "        It features a login system with user registration, a leaderboard showing wins, losses, and total score, plus saved game sessions.\n" +
            "        Gameplay includes drawing from and discarding to piles, handling special cards (Draw Two, Reverse, Skip, Wild, Wild Draw Four), Uno declarations, and penalties for failing to say “Uno.”\n" +
            "        All actions and game events are logged to text files, and player statistics (win/loss ratio, average score) are persisted and displayed via a Swing GUI.",
        gameDescriptionKnowledge:"Knowledge Siege is an educational Java/Swing game where you move an avatar to catch informative “ShotBoxes” and avoid harmful question boxes.\n" +
            "                    You face off against “Knowledge Keepers” (Section Leaders, TAs, and Professors) across three escalating levels, earning points and managing health.\n" +
            "                    Gameplay blends horizontal movement, timed shooting of boxes from the top, and collision detection driven by Swing timers.\n" +
            "                    The game loads content from external files, logs events, and reinforces OOP principles like inheritance, polymorphism, and encapsulation.",

        research:"Research",
        games:"Games",
        home:"University page",
        loginTitle: "Login to play UNO",
        username: "Username",
        password: "Password",
        loginButton: "Login",
        loginError: "Invalid username or password",
        play:"Play me!",
        firstgame:"First game:",
        secondgame:"Second game:",
        goToRegister: "Create account",
        goToLogin: "Already have an account? Log in",
        registerTitle: "Create an account",
        registerButton: "Sign up",
        passwordRepeat: "Repeat password",
        passwordRepeatPh: "Repeat your password",
        registerMissing: "Missing username or password",
        registerPwdMismatch: "Passwords do not match",
        registerUserExists: "User already exists",
        registerWriteError: "Could not save user",
        registerErrorGeneric: "Could not create account",
    },
    tr: {
        title:     "Çerez Bildirimi",
        desc:      "Çerezler, içeriği ve reklamları kişiselleştirmek, sosyal medya içerikleri sunmak ve trafiği analiz etmek için kullanılır. “Tümüne izin ver”i seçerek tüm çerezleri kabul edebilir veya “Çerez ayarlarını özelleştir” seçeneğiyle ayarlarınızı yapabilirsiniz. Herhangi bir web sitesini ziyaret ettiğinizde, çerezler biçiminde tarayıcınızda bilgi depolanabilir veya alınabilir. Bu bilgiler sizi, tercihlerinizi veya cihazınızı tanımlamaz, ancak sitenin düzgün çalışması ve size daha kişiselleştirilmiş bir deneyim sunması için kullanılır. Gizliliğinize saygı duyuyoruz; bu nedenle belirli çerezleri reddetmeyi tercih edebilirsiniz. Kategori başlıklarına tıklayarak daha fazla bilgi alabilir ve varsayılan ayarlarımızı değiştirebilirsiniz. Çerez politikamızı görmek için buraya tıklayın.",
        btnAccept: "Tümüne izin ver",
        btnReject: "Tümünü reddet",
        btnCustom: "Çerez ayarlarını özelleştir",
        researchTitle:" Araştırmamız ve hedeflerimiz",
        gameTitleSection:"Öğrencilerimizin yaptığı oyunlar",
        gameTitle:"UNO:",
        gameDescriptionUno:"Bu Uno Kart Oyunu simülatörü, bilgisayar \"botlarına\" karşı oynadığınız, renk, sayı veya sembol eşleştirmek için kart çekip oynadığınız bir Java/Swing uygulamasıdır.\n" +
            "Kullanıcı kaydı içeren bir giriş sistemi, galibiyetleri, mağlubiyetleri ve toplam puanı gösteren bir liderlik tablosu ve kaydedilmiş oyun oturumları içerir.\n" +
            "Oyun, destelerden kart çekme ve atma, özel kartları kullanma (İki Çek, Ters Çevir, Atla, Joker, Dört Çek), Uno bildirimleri ve \"Uno\" dememenin cezalarını içerir.\n" +
            "Tüm eylemler ve oyun etkinlikleri metin dosyalarına kaydedilir ve oyuncu istatistikleri (galibiyet/mağlubiyet oranı, ortalama puan) kalıcı olarak bir Swing GUI aracılığıyla görüntülenir.",
        gameDescriptionKnowledge: "Knowledge Siege, bilgilendirici \"ShotBox\"ları yakalamak ve zararlı soru kutularından kaçınmak için bir avatarı hareket ettirdiğiniz eğitici bir Java/Swing oyunudur. Üç kademeli olarak ilerleyen seviyelerde \"Bilgi Bekçileri\" (Bölüm Liderleri, Öğretim Görevlileri ve Profesörler) ile karşı karşıya gelir, puan kazanır ve sağlığınızı yönetirsiniz.\n" +
            "Oyun, yatay hareket, kutuların tepeden zamanlı olarak vurulması ve Swing zamanlayıcıları tarafından yönlendirilen çarpışma algılamayı bir araya getirir.\n" +
            "Oyun, harici dosyalardan içerik yükler, olayları kaydeder ve kalıtım, polimorfizm ve kapsülleme gibi OOP prensiplerini güçlendirir.",

        research: "Araştırma",
        games:"Oyunlar",
        home:"Üniversite sayfası",
        loginTitle: "UNO oynamak için giriş yapın",
        username: "Kullanıcı adı",
        password: "Şifre",
        loginButton: "Giriş",
        loginError: "Kullanıcı adı veya şifre hatalı",
        play:"Beni oyna!",
        firstgame:"İlk oyun:",
        secondgame:"İkinci oyun",
        goToRegister: "Hesap oluştur",
        goToLogin: "Zaten hesabın var mı? Giriş yap",
        registerTitle: "Hesap oluştur",
        registerButton: "Kayıt ol",
        passwordRepeat: "Şifreyi tekrar girin",
        passwordRepeatPh: "Şifrenizi tekrar girin",
        registerMissing: "Kullanıcı adı veya şifre eksik",
        registerPwdMismatch: "Şifreler eşleşmiyor",
        registerUserExists: "Bu kullanıcı zaten var",
        registerWriteError: "Kullanıcı kaydedilemedi",
        registerErrorGeneric: "Hesap oluşturulamadı",

    }
};
// Apply translations to page elements based on selected language
function applyI18n(lang) {
    const t = i18n[lang] || i18n.tr;

    // Translate elements with data-i18n attribute
    document.querySelectorAll('[data-i18n]').forEach(el => {
        const key = el.getAttribute('data-i18n');
        if (key && t[key] != null) el.innerText = t[key];
    });

    // Translate placeholders in input fields
    document.querySelectorAll('[data-i18n-placeholder]').forEach(el => {
        const key = el.getAttribute('data-i18n-placeholder');
        if (key && t[key] != null) el.setAttribute('placeholder', t[key]);
    });

    // Translate cookie banner text if present
    const banner = document.getElementById('cookie-banner');
    if (banner) {
        const h2 = banner.querySelector('h2');
        const p  = banner.querySelector('p');
        const accept = document.getElementById('accept-all');
        const reject = document.getElementById('reject-all');
        const customize = document.getElementById('customize');
        if (h2 && t.title) h2.innerText = t.title;
        if (p && t.desc) p.innerText = t.desc;
        if (accept && t.btnAccept) accept.innerText = t.btnAccept;
        if (reject && t.btnReject) reject.innerText = t.btnReject;
        if (customize && t.btnCustom) customize.innerText = t.btnCustom;
    }
}

// Set default language and enable language switching
document.addEventListener('DOMContentLoaded', () => {
    const activeBtn = document.querySelector('#lang-switcher .active');
    const navLang = (navigator.language || 'tr').slice(0, 2);
    const defaultLang = activeBtn ? activeBtn.dataset.lang : (i18n[navLang] ? navLang : 'tr');

    applyI18n(defaultLang);

    // Event listeners for language buttons
    document.querySelectorAll('#lang-switcher .lang-btn').forEach(btn => {
        btn.addEventListener('click', () => {
            applyI18n(btn.dataset.lang);
            const current = document.querySelector('#lang-switcher .active');
            if (current) current.classList.remove('active');
            btn.classList.add('active');
        });
    });
});

// Remove cookie consent (for testing purposes, so banner appears every time)
localStorage.removeItem('cookie_consent');

// Language switcher logic with simple banner translation (legacy)
document.querySelectorAll('#lang-switcher .lang-btn').forEach(btn => {
    btn.addEventListener('click', () => {
        const lang = btn.dataset.lang;
        translateBanner(lang);
        document.querySelector('#lang-switcher .active').classList.remove('active');
        btn.classList.add('active');
    });
});

// Initial language setup on page load (legacy)
document.addEventListener('DOMContentLoaded', () => {
    const defaultLang = 'tr';
    translateBanner(defaultLang);
    document.querySelectorAll('#lang-switcher .lang-btn').forEach(btn =>
        btn.classList.toggle('active', btn.dataset.lang === defaultLang)
    );
});
