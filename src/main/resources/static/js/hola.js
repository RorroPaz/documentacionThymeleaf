// Efecto de partículas
document.addEventListener('DOMContentLoaded', () => {
    const particlesContainer = document.getElementById('particles');
    const particleCount = window.innerWidth < 768 ? 20 : 40;

    for (let i = 0; i < particleCount; i++) {
        const particle = document.createElement('div');
        particle.classList.add('particle');

        // Tamaño aleatorio
        const size = Math.random() * 6 + 2;
        particle.style.width = `${size}px`;
        particle.style.height = `${size}px`;

        // Posición aleatoria
        particle.style.left = `${Math.random() * 100}vw`;
        particle.style.top = `${Math.random() * 100}vh`;

        // Opacidad aleatoria
        particle.style.opacity = Math.random() * 0.3 + 0.1;

        // Animación
        const duration = Math.random() * 20 + 10;
        particle.style.animationDuration = `${duration}s`;

        particlesContainer.appendChild(particle);
    }
});
