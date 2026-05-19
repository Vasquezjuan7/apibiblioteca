@echo off
echo ========================================
echo Subiendo proyecto a GitHub
echo ========================================
cd /d "c:\Users\juanv\OneDrive\Documentos\Apiblbliotecam\Apibiblioteca"

echo.
echo 1. Inicializando git...
git init

echo.
echo 2. Configurando usuario git (si es necesario)...
git config user.name "Juan Vasquez"
git config user.email "tu_email@ejemplo.com"

echo.
echo 3. Agregando todos los archivos...
git add .

echo.
echo 4. Haciendo commit inicial...
git commit -m "Proyecto API Biblioteca - Spring Boot con MongoDB"

echo.
echo 5. Agregando remote origin...
git remote add origin https://github.com/Vasquezjuan7/apibiblioteca.git

echo.
echo 6. Subiendo a GitHub (rama main)...
git branch -M main
git push -u origin main

echo.
echo ========================================
echo ¡Proyecto subido exitosamente!
echo ========================================
pause
