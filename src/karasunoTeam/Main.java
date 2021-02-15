package karasunoTeam;

import java.io.File;
import java.util.Scanner;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.Text;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        boolean close = true;
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        String fichero = "karasuno.xml";

        try {
            //Creamos un Document Builder
            DocumentBuilder builder = dbf.newDocumentBuilder();
            Document doc = builder.parse(new File(fichero));
            doc.getDocumentElement().normalize();

            //Nos dice el nombre del fichero
            System.out.println("Elemento raíz: " + doc.getDocumentElement().getNodeName());

            while (close) {
                System.out.println("¿Qué operación desea realizar?\n"
                        + "1. Listar miembros.\n"
                        + "2. Añadir miembro.\n"
                        + "3. Borrar miembro.\n"
                        + "4. Salir.");
                String operation = sc.nextLine();
                switch (operation) {
                    case "1":
                        //Listamos el contenido de los nodos del fichero xml
                        NodeList karasuno = doc.getElementsByTagName("miembro");
                        for (int i = 0; i < karasuno.getLength(); i++) {
                            //Accedemos al nodo
                            Node miembro = karasuno.item(i);
                            if (miembro.getNodeType() == Node.ELEMENT_NODE) {
                                //Queremos convertir nuestro nodo en un elemento
                                Element elemento = (Element) miembro;
                                //Tenemos que sacar todos los hijos de ese elemento
                                NodeList nodo = elemento.getElementsByTagName("nombre").item(0).getChildNodes();
                                Node valorNodo = (Node) nodo.item(0);

                                System.out.println("Nombre: " + valorNodo.getNodeValue());

                                //Otro nodo
                                NodeList nodo2 = elemento.getElementsByTagName("numero").item(0).getChildNodes();
                                Node valorNodo2 = (Node) nodo2.item(0);

                                System.out.println("Número: " + valorNodo2.getNodeValue());

                                //Otro nodo
                                NodeList nodo3 = elemento.getElementsByTagName("posicion").item(0).getChildNodes();
                                Node valorNodo3 = (Node) nodo3.item(0);

                                System.out.println("Posición: " + valorNodo3.getNodeValue());

                                //Otro nodo
                                NodeList nodo4 = elemento.getElementsByTagName("year").item(0).getChildNodes();
                                Node valorNodo4 = (Node) nodo4.item(0);

                                System.out.println("Año: " + valorNodo4.getNodeValue());

                                //Otro nodo
                                NodeList nodo5 = elemento.getElementsByTagName("estado").item(0).getChildNodes();
                                Node valorNodo5 = (Node) nodo5.item(0);

                                System.out.println("Estado: " + valorNodo5.getNodeValue());
                            }
                        }
                        break;
                    case "2":
                        //Para introducir datos nuevos en nuestro documento
                        System.out.println("Inserte el nombre: ");
                        String nombre = sc.nextLine();
                        System.out.println("Inserte el numero: ");
                        String numero = sc.nextLine();
                        System.out.println("Inserte el posicion: ");
                        String posicion = sc.nextLine();
                        System.out.println("Inserte el año: ");
                        String year = sc.nextLine();
                        System.out.println("Inserte el estado: ");
                        String estado = sc.nextLine();

                        //Creo un nuevo elemento miembro
                        Element nuevoMiembro = doc.createElement("miembro");
                        //appendChild - añade un hijo (en este caso, un miembro)
                        doc.getDocumentElement().appendChild(nuevoMiembro);

                        //Creo un nuevo elemento nombre
                        Element elemNombre = doc.createElement("nombre");//Creo el hijo nombre
                        Text textoNombre = doc.createTextNode(nombre);//Creo el texto que va dentro
                        nuevoMiembro.appendChild(elemNombre);
                        elemNombre.appendChild(textoNombre);

                        //Creo un nuevo elemento número
                        Element elemNumero = doc.createElement("numero");//Creo el hijo número
                        Text textoNumero = doc.createTextNode(numero);//Creo el texto que va dentro
                        nuevoMiembro.appendChild(elemNumero);
                        elemNumero.appendChild(textoNumero);

                        //Creo un nuevo elemento posición
                        Element elemPosicion = doc.createElement("posicion");//Creo el hijo posición
                        Text textoPosicion = doc.createTextNode(posicion);//Creo el texto que va dentro
                        nuevoMiembro.appendChild(elemPosicion);
                        elemPosicion.appendChild(textoPosicion);

                        //Creo un nuevo elemento año
                        Element elemYear = doc.createElement("year");//Creo el hijo año
                        Text textoYear = doc.createTextNode(year);//Creo el texto que va dentro
                        nuevoMiembro.appendChild(elemYear);
                        elemYear.appendChild(textoYear);

                        //Creo un nuevo elemento estado
                        Element elemEstado = doc.createElement("estado");//Creo el hijo estado
                        Text textoEstado = doc.createTextNode(estado);//Creo el texto que va dentro
                        nuevoMiembro.appendChild(elemEstado);
                        elemEstado.appendChild(textoEstado);
                        break;
                    case "3":
                        //Eliminar un alumno según su nombre
                        System.out.print("Inserta el nombre del alumno que quieres borrar: ");
                        String miembroBorrar = sc.nextLine();

                        NodeList karasuno2 = doc.getElementsByTagName("miembro");
                        for (int i = 0; i < karasuno2.getLength(); i++) {
                            Node miembro = karasuno2.item(i);//Obtener nodo

                            if (miembro.getNodeType() == Node.ELEMENT_NODE) {//Tipo de nodo
                                Element elemento = (Element) miembro; //Obtener elemento del nodo (transformar un nodo en un elemento)

                                NodeList nodo = elemento.getElementsByTagName("nombre").item(0).getChildNodes();
                                Node valorNodo = (Node) nodo.item(0);//Obteniendo el nodo de texto

                                if (valorNodo.getNodeValue().equals(miembroBorrar)) {
                                    miembro.getParentNode().removeChild(miembro); //Accedo al nodo padre y desde ahí borro el hijo
                                }
                            }
                        }
                        break;
                    case "4":
                        close = false;

                        break;
                    default:
                        System.out.println("Operación no válida.");
                }
                //Hemos escrito en un documento nuevo
                //Como el documento no es nuestro fichero
                //Tenemos que reescribir nuestro fichero
                Source source = new DOMSource(doc);//Fuente de datos
                //Por lo que abrimos un flujo de datos a nuestro fichero
                Result result = new StreamResult("nuevaListaMiembros.xml");
                //Y transformamos nuestro fichero
                Transformer transformer = TransformerFactory.newInstance().newTransformer();
                transformer.transform(source, result);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        sc.close();
    }
}
