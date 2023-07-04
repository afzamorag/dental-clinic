function registerPatient() {
  const name = document.getElementById("name").value;
  const lastName = document.getElementById("lastName").value;
  const idNumber = document.getElementById("idNumber").value;
  const email = document.getElementById("email").value;
  const dateRegister = document.getElementById("dateRegister").value;
  const street = document.getElementById("street").value;
  const streetNumber = document.getElementById("streetNumber").value;
  const location = document.getElementById("location").value;
  const city = document.getElementById("city").value;

  if (name === '' || lastName === '' || idNumber === '' || email === '' || dateRegister === '' ) {
    alert('Por favor, complete todos los campos.');
    return;
  }

  const patientData = {
    id: Math.floor(Math.random() * (10 - 1 + 1)) + 1,
    name: name,
    lastName: lastName,
    homeAddress:{
       street: street,
       streetNumber: streetNumber,
       location: location,
       city: city
    },
    idNumber: idNumber,
    email: email,
    dateRegister: dateRegister,
  };

  fetch('http://localhost:8080/patients', {
    method: 'POST',
    headers: {
      'Content-Type': 'application/json'
    },
    body: JSON.stringify(patientData)
  })
    .then(response => response.json())
       .then(data => {
        alert('El paciente ' + data.name + ' ' + data.lastName + ' ha sido registrado exitosamente.');
        document.getElementById("registerForm").reset();
    })
    .catch(error => {
      console.error('Error:', error);
      alert('Hubo un error al registrar el paciente. Por favor, intenta nuevamente.');
    });
}
