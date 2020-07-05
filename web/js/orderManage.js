function commodityChangeHandler(ev) {
    let event =  ev || window.event; //兼容IE

    console.log(event.target)
    let activeOption = null;

    Array.prototype.forEach.call(event.target.children,(child)=>{
        if(child.selected){
            activeOption = child;
            return;
        }
    })
    console.log(activeOption)

    event.target.parentNode.children[1].innerHTML = activeOption.getAttribute("price") + '元'
}

console.log('loadScript')
