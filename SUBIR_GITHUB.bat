@echo off
chcp 65001 >nul
setlocal enabledelayedexpansion

echo.
echo ==========================================
echo   SUBIENDO PROYECTO A GITHUB
echo ==========================================
echo.

cd /d "c:\Users\juanv\OneDrive\Documentos\Apiblbliotecam\Apibiblioteca"

echo [1/8] Verificando estado actual de git...
git status
echo.

echo [2/8] Configurando git (si es necesario)...
git config user.name "Juan Vasquez"
git config user.email "juanvasquezsanchez2006@gmail.com"
echo ✓ Configuración completada
echo.

echo [3/8] Agregando todos los archivos...
git add .
echo ✓ Archivos agregados
echo.

echo [4/8] Mostrando archivos a comitear...
git status
echo.

echo [5/8] Haciendo commit...
git commit -m "Proyecto API Biblioteca - Spring Boot con MongoDB - Funcionando correctamente"
echo ✓ Commit realizado
echo.

echo [6/8] Verificando rama...
git branch -v
echo.

echo [7/8] Configurando rama main...
git branch -M main
echo ✓ Rama configurada como main
echo.

echo [8/8] Verificando remote...
git remote -v
if errorlevel 1 (
    echo No existe remote, agregando...
    git remote add origin https://github.com/Vasquezjuan7/apibiblioteca.git
) else (
    echo Remote ya existe
)
echo.

echo ==========================================
echo   SUBIENDO A GITHUB...
echo ==========================================
echo.
git push -u origin main

if errorlevel 0 (
    echo.
    echo ==========================================
    echo   ✓ ¡ÉXITO! Proyecto subido a GitHub
    echo ==========================================
    echo.
    echo Verifica en: https://github.com/Vasquezjuan7/apibiblioteca
    echo.
) else (
    echo.
    echo ==========================================
    echo   ✗ ERROR al subir
    echo ==========================================
    echo.
    echo Posibles soluciones:
    echo 1. Verifica tu conexión a internet
    echo 2. Revisa que tu token de GitHub sea válido
    echo 3. Asegúrate que el repositorio existe en GitHub
    echo.
)

pause
