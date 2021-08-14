(()=>{
	const profileImage = document.getElementById('profileImage');
	const img = document.getElementById('img');
	img.style.display = "none";
	const imgList = img.querySelectorAll('[data-img]');
	let count = 0;
	
	profileImage.addEventListener('click',()=>{
		img.style.display="block";
	})
	while(count < imgList.length){
		imgList[count].addEventListener('click',(e)=>changeImage(e));
		count++;
	}
	
	const changeImage=(e)=>{
		profileImage.src = e.target.src;
		document.getElementById('hidden').value=e.target.src;
	}
})();