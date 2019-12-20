class Platillo {
    String nombre;
    double precio;
    char desc;

    Platillo(String nombrePlatillo, double precioPlatillo, char oferta) {
        nombre = nombrePlatillo;
        precio = precioPlatillo;
    }

    public Platillo(){
        nombre = "";
        precio = 0;
    }

    public String getNombre() {
        return nombre;
    }

    public double getPrecio() {
        return precio;
    }

    public char getOferta() {
        return desc;
    }

    public void setOferta(char configurarOferta) {
        desc = configurarOferta;
    }
}