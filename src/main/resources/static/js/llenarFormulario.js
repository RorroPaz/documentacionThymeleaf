document.addEventListener('DOMContentLoaded', function() {
    console.log("Script de autollenado cargado - iniciando proceso...");
    
    // 1. Localizar el formulario
    const form = document.querySelector('form');
    if (!form) {
        console.error("Error: No se encontró el formulario en la página");
        return;
    }
    console.log("Formulario encontrado con éxito");

    // 2. Función principal de autollenado
    function autollenarFormulario() {
        // Campos básicos del módulo
        const camposBasicos = {
            'desarrollador': 'Equipo de Desarrollo',
            'nombreModulo': 'Módulo de Gestión Avanzada',
            'rutaModulo': '/admin/gestion-avanzada',
            'javascript': 'gestionAvanzada.js',
            'jsp': 'gestionAvanzada.jsp',
            'pojo': 'GestionAvanzada.java',
            'servicio': 'GestionAvanzadaService.java',
            'repositorio': 'GestionAvanzadaRepository.java',
            'controlador': 'GestionAvanzadaController.java'
        };

        // Llenar campos básicos
        Object.entries(camposBasicos).forEach(([name, value]) => {
            const campo = form.querySelector(`input[name="${name}"]`);
            if (campo) {
                campo.value = value;
                console.log(`Campo ${name} llenado con: ${value}`);
            } else {
                console.warn(`Campo no encontrado: ${name}`);
            }
        });

        // 3. Manejo de APIs
        const btnAddApi = document.getElementById('btn-add-api');
        if (btnAddApi) {
            console.log("Preparando para agregar APIs...");
            
            // Datos de ejemplo para APIs
            const apisEjemplo = [
                {
                    'nombreApi': 'obtenerRegistros',
                    'tipo': 'GET',
                    'ruta': '/api/registros',
                    'datosRegresa': 'Lista paginada de registros',
                    'parametros': 'pagina, tamanio, filtro',
                    'descripcion': 'Obtiene registros con paginación',
                    'comentarios': 'Requiere autenticación básica'
                },
                {
                    'nombreApi': 'crearRegistro',
                    'tipo': 'POST',
                    'ruta': '/api/registros',
                    'datosRegresa': 'Registro creado con ID',
                    'parametros': 'Datos del registro en body',
                    'descripcion': 'Crea un nuevo registro en el sistema',
                    'comentarios': 'Requiere rol de administrador'
                }
            ];

            // Función para llenar una sección de API
            function llenarApi(section, apiData, index) {
                Object.entries(apiData).forEach(([field, value]) => {
                    // Los nombres de campos en APIs usan array: apis[0].nombreApi
                    const namePattern = `apis[${index}].${field}`;
                    const selector = `input[name="${namePattern}"], 
                                      select[name="${namePattern}"], 
                                      textarea[name="${namePattern}"]`;
                    
                    const elemento = section.querySelector(selector);
                    if (elemento) {
                        elemento.value = value;
                        // Disparar evento change para actualizar cualquier listener
                        elemento.dispatchEvent(new Event('change'));
                        console.log(`API ${index+1} - ${field}: ${value}`);
                    }
                });
            }

            // Asegurar que hay al menos una sección de API
            let apiSections = document.querySelectorAll('.api-section');
            if (apiSections.length === 0) {
                btnAddApi.click();
                apiSections = document.querySelectorAll('.api-section');
            }

            // Llenar las APIs disponibles
            apisEjemplo.forEach((api, index) => {
                if (index >= apiSections.length) {
                    btnAddApi.click();
                    apiSections = document.querySelectorAll('.api-section');
                }
                llenarApi(apiSections[index], api, index);
            });
        } else {
            console.warn("No se encontró el botón para agregar APIs");
        }
    }

    // 4. Ejecutar después de un breve retraso para asegurar que todo esté listo
    setTimeout(autollenadoFormulario, 500);
    
    // 5. Opcional: Agregar botón para autollenado manual
    const botonAutollenado = document.createElement('button');
    botonAutollenado.textContent = 'Autollenar Formulario';
    botonAutollenado.type = 'button';
    botonAutollenado.style.margin = '10px';
    botonAutollenado.style.padding = '8px 15px';
    botonAutollenado.style.backgroundColor = '#4CAF50';
    botonAutollenado.style.color = 'white';
    botonAutollenado.style.border = 'none';
    botonAutollenado.style.borderRadius = '4px';
    botonAutollenado.style.cursor = 'pointer';
    
    botonAutollenado.addEventListener('click', function() {
        console.log("Ejecutando autollenado manual...");
        autollenadoFormulario();
    });
    
    // Insertar el botón al inicio del formulario
    form.insertBefore(botonAutollenado, form.firstChild);
});