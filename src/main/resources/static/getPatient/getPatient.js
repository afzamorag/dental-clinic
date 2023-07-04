
// getPatient.js
function getAllPatients() {

function createActionButton(id) {
  const deleteIcon = '<i class="fas fa-trash-alt text-danger" onclick="deletePatient(' + id + ')"></i>';
  const updateIcon = '<i class="fas fa-pencil-alt text-primary" onclick="updatePatient(' + id + ')"></i>';
  return deleteIcon + ' ' + updateIcon;
}

  fetch('http://localhost:8080/patients')
    .then(response => response.json())
    .then(data => {
      const patientsBody = document.getElementById('patients-body');
      patientsBody.innerHTML = '';
      data.forEach(patient => {
        const { name, lastName, homeAddress, idNumber, dateRegister } = patient;
        const row = document.createElement('tr');
         row.id = 'patient-row-' + id;
        row.innerHTML = `
          <td>${name}</td>
          <td>${lastName}</td>
          <td>${homeAddress.street} ${homeAddress.streetNumber}, ${homeAddress.location}, ${homeAddress.city}</td>
          <td>${idNumber}</td>
          <td>${dateRegister}</td>
        `;
        patientsBody.appendChild(row);
      });
    })
    .catch(error => {
      console.error('Error:', error);
    });
}

window.onload = getAllPatients;

function deletePatient(id) {
            fetch('http://localhost:8080/patients/delete/' + id, {
                    method: 'DELETE'
                })
                .then(response => {
                    if (response.ok) {
                        const row = document.getElementById('dentist-row-' + id);
                        row.remove();
                    } else {
                        throw new Error('Error al eliminar el paciente');
                    }
                })
                .catch(error => {
                    console.error('Error:', error);
            });

 }

 function updatePatient(id) {
    fetch('http://localhost:8080/patients/update' + id, {
      method: 'PUT',
      headers: {
        'Content-Type': 'application/json'
      },
      body: JSON.stringify(updatedPatient)
    })
      .then(response => response.json())
      .then(updatedData => {
        const row = document.getElementById('patient-row-' + id);
        row.innerHTML = `
          <td>${updatedData.name}</td>
          <td>${updatedData.lastName}</td>
          <td>${updatedData.homeAddress.street} ${updatedData.homeAddress.streetNumber}, ${updatedData.homeAddress.location}, ${updatedData.homeAddress.city}</td>
          <td>${updatedData.idNumber}</td>
          <td>${updatedData.dateRegister}</td>
          <td>${createActionButton(id)}</td>
        `;
      })
      .catch(error => {
        console.error('Error:', error);
      });
  }