public class PD9 {

    // EJERCICIO 1 - Calcular el factorial de un número usando bucles for
    public static int factorial(int num) {
        if (num < 0) {
            throw new IllegalArgumentException("El número debe ser no negativo.");
        }
        int resultado = 1;
        for (int i = 1; i <= num; i++) {
            resultado *= i;
        }
        return resultado;
    }

    // EJERCICIO 2 - Verificar si un número es primo y realizar cálculos adicionales
    public static boolean isPrime(long n) {
        if (n < 2) {
            return false;
        }
        if (n == 2) {
            return true;
        }
        if (n % 2 == 0) {
            return false;
        }
        boolean prime = true;
        long limite = (long) Math.sqrt(n);
        long i = 3;
        while (i <= limite) {
            if (n % i == 0) {
                prime = false;
                break;
            }
            i += 2;
        }
        return prime;
    }

    public static void calcularSuma(int num) {
        long suma = 0;
        int i = 0;

        if (isPrime(num)) {
            System.out.println(num + " es un número primo.");
            // Suma de los números pares desde 0 hasta num
            while (i <= num) {
                if (i % 2 == 0) {
                    suma += i;
                }
                i++;
            }
            System.out.println("La suma de los números pares desde 0 hasta " + num + " es: " + suma);
        } else {
            System.out.println(num + " no es un número primo.");
            // Suma de los números impares desde 0 hasta num
            while (i <= num) {
                if (i % 2 != 0) {
                    suma += i;
                }
                i++;
            }
            System.out.println("La suma de los números impares desde 0 hasta " + num + " es: " + suma);
        }
    }

    public static void main(String[] args) {
        // prueba calculo factorial
        int numeroFactorial = 5;
        System.out.println("Factorial de " + numeroFactorial + " es: " + factorial(numeroFactorial));

        // prueba promo y suma
        int numeroPrimo = 7;
        calcularSuma(numeroPrimo);
    }
}
