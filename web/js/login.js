window.onload = function() {
    let targets = document.querySelectorAll('.willAnimate');
    let timeline = anime.timeline({
        easing: 'easeInExpo',
        duration: 400
    });
    timeline.add({
        targets,
        opacity: [.2, 1]
    })
    anime({
        targets: '#animateImg',
        opacity: [.2, 1],
        duration: 1500,
        translateY: [-100, 50]
    })
}