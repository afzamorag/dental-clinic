function loadPatientsRegister() {
      fetch('http://localhost:8080/patients')
        .then(response => response.json())
        .then(data => {
          const select = document.getElementById('patient');
          select.innerHTML = '';
          data.forEach(patient => {
            const option = document.createElement('option');
            option.value = patient.id;
            option.textContent = patient.name;
            select.appendChild(option);
          });
        })
        .catch(error => {
          console.error('Error:', error);
        });
    }

    function loadDentistRegister() {
      fetch('http://localhost:8080/dentist')
        .then(response => response.json())
        .then(data => {
          const select = document.getElementById('dentist');
          select.innerHTML = '';
          data.forEach(dentist => {
            const option = document.createElement('option');
            option.value = dentist.id;
            option.textContent = dentist.name;
            select.appendChild(option);
          });
        })
        .catch(error => {
          console.error('Error:', error);
        });
    }

    // Registrar turno
    function registerTurn(event) {
      event.preventDefault();
      const patientId = document.getElementById('patient').value;
      const dentistId = document.getElementById('dentist').value;
      const dateTurn = document.getElementById('dateTurn').value;

      const turn = {
        patient: { id: patientId },
        dentist: { id: dentistId },
        dateTurn: dateTurn
      };

      fetch('http://localhost:8080/turn', {
        method: 'POST',
        headers: {
          'Content-Type': 'application/json'
        },
        body: JSON.stringify(turn)
      })
        .then(response => {
          if (response.ok) {
            alert('El turno ha sido registrado exitosamente.');
            document.getElementById('turnCard').style.display = 'block';
            document.getElementById('turnForm').reset();
          } else {
            alert('Hubo un error al registrar el turno. Por favor, inténtelo nuevamente.');
          }
        })
        .catch(error => {
          console.error('Error:', error);
        });
    }

    window.onload = function () {
      loadPatientsRegister();
      loadDentistRegister();
      document.getElementById('turnForm').addEventListener('submit', registerTurn);
    };