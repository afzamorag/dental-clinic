// getDentist.js

function getAllDentist() {

        function createActionButton(id) {
            const deleteIcon = '<i class="fas fa-trash-alt text-danger" onclick="deleteDentist(' + id + ')"></i>';
            const updateIcon = '<i class="fas fa-pencil-alt text-primary" onclick="updateDentist(' + id + ')"></i>';
            return deleteIcon + ' ' + updateIcon;
        }

  fetch('http://localhost:8080/dentist')
    .then(response => response.json())
    .then(data => {
      const dentistBody = document.getElementById('dentist-body');
      dentistBody.innerHTML = '';
      data.forEach(patient => {
        const { id, registrationNumber, name, lastName } = patient;
        const row = document.createElement('tr');
        row.id = 'dentist-row-' + id;
        row.innerHTML = `
          <td>${name}</td>
          <td>${lastName}</td>
          <td>${registrationNumber}</td>
          <td>${createActionButton(id)}</td>
        `;
        dentistBody.appendChild(row);
      });
    })
    .catch(error => {
      console.error('Error:', error);
    });
}

window.onload = getAllDentist;

function deleteDentist(id) {
            fetch('http://localhost:8080/dentist/delete/' + id, {
                    method: 'DELETE'
                })
                .then(response => {
                    if (response.ok) {
                        const row = document.getElementById('dentist-row-' + id);
                        row.remove();
                    } else {
                        throw new Error('Error al eliminar el dentista');
                    }
                })
                .catch(error => {
                    console.error('Error:', error);
            });

 }

function updateDentist(id) {
    const updatedData = {
        registrationNumber: document.getElementById('registrationNumber').value,
        name: document.getElementById('name').value,
        lastName: document.getElementById('lastName').value
    };

    fetch('http://localhost:8080/dentist/update' + id, {
        method: 'PUT',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(updatedData)
    })
    .then(response => {
        if (response.ok) {
            const row = document.getElementById('dentist-row-' + id);
            row.children[2].textContent = updatedData.registrationNumber;
            row.children[0].textContent = updatedData.name;
            row.children[1].textContent = updatedData.lastName;
        } else {
            throw new Error('Error al actualizar el dentista');
        }
    })
    .catch(error => {
        console.error('Error:', error);
    });
}

