package repaso;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class EstructuraDeControl {

    public static String name;
    public static String email;
    public static String password;
    public static Double weight;
    public static Double size;
    public static boolean state = false;
    public static boolean isActived;
    public static List<String[]> imcs = new ArrayList<>();
    public static List<String[]> usuarios = new ArrayList<>();
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        boolean isOn = stateChange();
        while (isOn)
            isOn = menu();
    }

    public static void register() {
        System.out.println("Escriba su nombre: ");
        name = sc.next();
        System.out.println("Escriba su correo: ");
        email = sc.next();
        System.out.println("Escriba su contraseña: ");
        password = sc.next();
        String[] usuario = new String[3];
        /*
        usuario[0] = name;
        usuario[1] = email;
        usuario[2] = password;
        usuarios.add(usuario);
        */
        usuarios.add(new String[]{name, email, password});
    }

    public static void login() {
        System.out.println("Ingrese su correo: ");
        String userMail = sc.next();
        System.out.println("Ingrese su contraseña: ");
        String userPass = sc.next();
        for ( String[] usuario : usuarios) {
            if (userMail.equals(usuario[1]) && userPass.equals(usuario[2])) {
                System.out.println("Acceso concedido");
                isActived = true;
                name = usuario[0];
                email = usuario[1];
                password = usuario[2];
                return;
            }
        }
        System.out.println("Datos incorrectos");
    }

    public static boolean menu() {
        System.out.println("1: Registrar\n 2: Login \n 3: Apagar sistema");
        int opc = sc.nextInt();
        switch (opc) {
            case 1:
                System.out.println("Iniciar registro");
                register();
                break;
            case 2:
                System.out.println("Iniciar login");
                login();
                if (isActived) menuApp();
                break;
            case 3:
                System.out.println("Salir");
                return false;
            default:
                System.out.println("Ingrese un dato valido");
                break;
        }
        return true;
    }

    public static boolean stateChange() {
        System.out.println("desea encender la aplicacion");
        int response = sc.nextInt();
        if (response == 1) state = true;
        return state;
    }

    public static void calculateIMC() {
        try {
            System.out.println("Ingresa altura");
            size = sc.nextDouble();
            System.out.println("Ingresa peso");
            weight = sc.nextDouble();
            double imc = weight / (size * size);
            if (imc <= 18.5) System.out.println("Peso bajo");
            else if (imc > 18.5 && imc <= 24.9) System.out.println("Peso normal");
            else if (imc > 24.9 && imc <= 29.9) System.out.println("Sobrepeso");
            else System.out.println("obesidad");
            imcs.add(new String[]{name, email, password, imc + ""});
        } catch (Exception e){
            System.out.println("Use coma para la estatura");
            //menuApp();
        };
    }

    public static void menuApp(){
        do {
            System.out.println("\n1. Registrar IMC \n" +
                    "2. Listar IMC\n" +
                    "3. Volver al Home");
            System.out.println("Ingrese una opción: ");
            int opc = sc.nextInt();
            switch (opc) {
                case 1:
                    System.out.println("\nRegistre IMC");
                    calculateIMC();
                    break;
                case 2:
                    System.out.println("\nListar los IMC registrados por el usuario");
                    listarIMC();
                    // listarIMC2();
                    // listarIMC3();
                    break;
                case 3:
                    System.out.println("Volver al Home");
                    isActived = false;
                default:
                    System.out.println("Ingrese una opción correcta");
            }
        } while(isActived);
    }

    public static void listarIMC3() {
        // Este es un forEach de tipo expresión lambda del paradigma de programación funcional
        imcs.forEach(imc -> {
            System.out.println("Registro: Su imc es de " + imc);
        });
    }

    public static void listarIMC2() {
        // Este es un for inicial o más común para recorrer lo que se desee
        for (int i = 0; i < imcs.size(); i++)
            System.out.println("Registro N°" + (i + 1) + ": Su imc es de " + imcs.get(i));
    }

    public static void listarIMC() {
        // Este es un forEach normalmente utilizado para recorrer procesos más rigurosos, objetos o estructuras de datos complejas
        int index = 0, j = 0;
        for ( String[] imc : imcs ) {
            System.out.println("Registro N°" + (index + 1) + ":\n" +
                    " - Name: " + imc[0] + ".\n" +
                    " - Email: " + imc[1] + ".\n" +
                    " - Password: " + imc[2] + ".\n" +
                    " - IMC: Su imc es de " + imc[3]);
            index++;
        }
    }
}
