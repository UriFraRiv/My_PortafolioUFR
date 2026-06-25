# Sistema Integral de Reportes Industriales - Android Nativo & Multiplataforma

A continuación, se presenta una demostración en video (1 min 20 s) del funcionamiento de la aplicación móvil nativa y su ecosistema de sincronización automatizada:

[▶️ Haz clic aquí para ver el video de demostración](LINK_)

---

### 📝 Descripción del Proyecto
Este proyecto fue diseñado para resolver la necesidad de levantar reportes multimedia en entornos industriales de manera eficiente. Consta de una aplicación móvil nativa enfocada en la experiencia del operario en campo, conectada a un ecosistema multiplataforma que automatiza la entrega de información directamente al área administrativa sin intervención manual.

---

### 🏗️ Arquitectura y Flujo de Información

El sistema opera bajo un flujo centralizado que garantiza la consistencia de los datos:

1. **Captura (Android Nativo):** El usuario gestiona su sesión, llena el reporte industrial con validaciones dinámicas y carga evidencias en la app.
2. **Centralización (Firebase):** Los datos se sincronizan en tiempo real y las imágenes se almacenan de forma segura en la nube.
3. **Consulta y Automatización (Web & Node.js):** Una interfaz web permite visualizar los registros recopilados, mientras un script automatizado estructura la información para su uso administrativo.

| 1. Base de Datos en la Nube | 2. Panel de Consulta Web | 3. Reporte Administrativo Final |
| :---: | :---: | :---: |
| *(Firebase)* | *( Web)* | *(Excel)* |
| *Consistencia de datos y Auth en Firebase* | *Consulta de reportes en tiempo real* | *Sincronización automatizada con Excel Online* |

> 📌 *Nota: Las imágenes del ecosistema web y base de datos se actualizarán próximamente.*

---

### 🚀 Características Técnicas

#### 📱 Desarrollo Android Nativo (Fuerte Principal)
* **Interfaz de Usuario:** Construida al 100% con **Jetpack Compose** y Material 3, garantizando vistas reactivas, modernas y adaptadas a entornos de trabajo.
* **Arquitectura:** Implementación estricta del patrón de diseño **MVVM (Model-View-ViewModel)** y Programación Orientada a Objetos para mantener la lógica de negocio completamente desacoplada de la interfaz.
* **Navegación:** Menú lateral dinámico (**ModalNavigationDrawer**) integrado mediante *Jetpack Navigation Component* para un flujo limpio tipo *Single Page Application*.
* **Control de Estados:** Gestión reactiva de formularios utilizando `remember` y `mutableStateOf` (incluyendo máscaras de visibilidad para contraseñas y deshabilitación de botones durante procesos de red).
* **Feedback Visual:** Pantallas de carga (Splash Screens) y transiciones fluidas optimizadas mediante la renderización de animaciones **Lottie (JSON)**.

#### ☁️ Integración y Ecosistema (Estructura y Conectividad)
* **Autenticación:** Control seguro de acceso de usuarios mediante *Firebase Authentication*.
* **Persistencia Cloud:** Almacenamiento de reportes estructurados en *Firestore Realtime Database* y carga eficiente de evidencias fotográficas vía *Firebase Storage* utilizando el manejo de flujos locales (*InputStreams*).
* **Automatización:** Integración lógica de un script en *Node.js* para mapear flujos de datos en formato JSON y transcribirlos automáticamente hacia hojas de cálculo de *Excel Online* para uso administrativo.

---
### 🛠️ Tecnologías Utilizadas
* **Lenguaje:** Kotlin
* **Framework UI:** Jetpack Compose (Material 3)
* **Backend as a Service (BaaS):** Firebase (Auth, Firestore, Storage)
* **Herramientas de Integración:** Node.js, JSON, Git / GitHub
