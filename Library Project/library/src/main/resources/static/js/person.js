function openCreateModal() {
    document.getElementById("modalTitle").textContent = "ثبت شخص جدید";
    document.getElementById("submitBtn").textContent = "ذخیره";
    document.getElementById("personForm").reset();
    document.getElementById("personId").value = "";
    document.getElementById("personModal").style.display = "block";
}

function openEditModal(id, firstName, lastName, nationalCode, fatherName, birthDate, gender, phoneNumber) {
    document.getElementById("modalTitle").textContent = "ویرایش شخص";
    document.getElementById("submitBtn").textContent = "به‌روزرسانی";

    document.getElementById("personId").value = id;
    document.getElementById("firstName").value = firstName;
    document.getElementById("lastName").value = lastName;
    document.getElementById("nationalCode").value = nationalCode;
    document.getElementById("fatherName").value = fatherName;
    document.getElementById("birthDate").value = birthDate;
    document.getElementById("gender").value = gender;
    document.getElementById("phoneNumber").value = phoneNumber;

    document.getElementById("personModal").style.display = "block";
}

function closeModal() {
    document.getElementById("personModal").style.display = "none";
}

window.onclick = function(event) {
    const modal = document.getElementById("personModal");
    if (event.target === modal) closeModal();
}