(()=>{
	const contactus = document.getElementById('contactus');
	const dialog = document.getElementById('dialog');
	const submit = document.getElementById('submit');
	const close = document.getElementById('close');
	const errorText = document.getElementById('errorText');
	const dialogText = document.getElementById('dialogText');
	const dialogSelect = document.getElementById('dialogSelect');
	
	contactus.addEventListener('click',()=>{
		errorText.style.display='none';
		dialogText.value="";
		dialogSelect.value="";
		dialog.showModal();
	})
	dialog.addEventListener('cancel',(e)=>{
		e.preventDefault();
	})
	submit.addEventListener('click',()=>{
		const textValue = dialogText.value;
		const selectValue = dialogSelect.value;
		if(textValue.length <= 0 || selectValue === ""){
			errorText.style.display='block';
		}else{
		fetch("/contactus?text=" + textValue + "&select=" + selectValue,{
			method:"get"
		}).then(response => {
			return response.text();
		}).then(body =>{
			window.alert(body);
			dialog.close();
		})

		}
	})
	
	
	close.addEventListener('click',()=>{
		dialog.close();
	})
	
	
})();