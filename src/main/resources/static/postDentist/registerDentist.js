function registerDentist() {
  const name = document.getElementById("name").value;
  const lastName = document.getElementById("lastName").value;
  const registration = document.getElementById("registrationNumber").value;

  if (name === '' || lastName === '' || registration === '') {
    alert('Por favor, complete todos los campos.');
    return;
  }

  const dentistData = {
    id: Math.floor(Math.random() * (10 - 1 + 1)) + 1,
    name: name,
    lastName: lastName,
    registrationNumber: parseInt(registration)
  };

  fetch('http://localhost:8080/dentist', {
    method: 'POST',
    headers: {
      'Content-Type': 'application/json'
    },
    body: JSON.stringify(dentistData)
  })
  .then(response => response.json())
  .then(data => {
    alert('El odontólogo ' + data.name + ' ' + data.lastName + ' ha sido registrado exitosamente.');
    document.getElementById("registerForm").reset();
  })
  .catch(error => {
    console.error('Error:', error);
    alert('Ocurrió un error al registrar el odontólogo.');
  });
}
