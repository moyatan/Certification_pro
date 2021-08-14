(()=>{
const test = document.getElementById('test');
const test3 = document.getElementById('profileImage');
let imgId;
console.log(test3);
console.log(test);
const $content = test.querySelectorAll('[data-img]');
console.log($content);
let count = 0;
while(count < $content.length){
$content[count].addEventListener('click',(e)=>test2(e));
count ++;
}

const test2=(e)=>{
console.log(e.target.src);
test3.src=e.target.src;
imgId = e.target.src;
const a = document.getElementById('aaaa');
console.log(a);
a.value=imgId;
console.log(a);
}
})();