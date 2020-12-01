/**
 * 2020-10-17
 * Clase CitiBike.
 * @author Julián Andrés Méndez
 */
package model.logic;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.opencsv.CSVParser;
import com.opencsv.CSVParserBuilder;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.exceptions.CsvValidationException;
import com.sun.org.apache.xerces.internal.impl.xpath.regex.ParseException;

import model.structures.algorithms.DijkstraSP;
import model.structures.algorithms.KosarajuSCC;
import model.structures.directedGraph.DiGraph;
import model.structures.directedGraph.Edge;
import model.structures.directedGraph.Vertex;
import model.structures.listaGenerica.ArregloDinamicoGenerico;

/**
 * Representa el sistema de bicicletas CitiBike.
 */
public class CitiBike {

	// -----------------------------------------------------------------
	// Constantes
	// -----------------------------------------------------------------

	/**
	 * Representa el separador al momento de mostrar un encabezado en el visor.
	 */
	public static final String SEPARADOR = "----------------------------------------------";


	/**
	 * Constante que representa la ruta de datos del primer mes.
	 */
	public static final String PRIMER_MES = "data/201801-1-citibike-tripdata.csv";

	/**
	 * Constante que representa la ruta de datos del segundo mes.
	 */
	public static final String SEGUNDO_MES = "data/201801-2-citibike-tripdata.csv";

	/**
	 * Constante que representa la ruta de datos del tercer mes.
	 */
	public static final String TERCER_MES = "data/201801-3-citibike-tripdata.csv";

	/**
	 * Constante que representa la ruta de datos del cuarto mes.
	 */
	public static final String CUARTO_MES = "data/201801-4-citibike-tripdata.csv";

	// -----------------------------------------------------------------
	// Atributos
	// -----------------------------------------------------------------

	/**
	 * Representa la estructura de datos de grafo dirigido.
	 */
	public DiGraph<Integer, Estacion, Turista> DiGraph;

	/**
	 * Representa los datos fuentes a ser cargados en el sistema.
	 */
	public String data;


	/**
	 * Representa las 3 estaciones principales a las que mas bicicletas llegan provenientes de otras estaciones.
	 */
	ArregloDinamicoGenerico<Vertex<Integer, Estacion, Turista>> TOPmayorLlegada = new ArregloDinamicoGenerico<Vertex<Integer, Estacion, Turista>>(3);

	/**
	 * Representa las 3 estaciones de las que más viajes salen hacia otras estaciones
	 */
	ArregloDinamicoGenerico<Vertex<Integer, Estacion, Turista>> TOPmayorSalida = new ArregloDinamicoGenerico<Vertex<Integer, Estacion, Turista>>(3);

	/**
	 *  Representa la lista de las 3 estaciones menos utilizadas por los turistas, esto es las estaciones de la que menos viajes salen y a la que menos viajes llegan (se considera la suma de ambos valores)
	 */
	ArregloDinamicoGenerico<Vertex<Integer, Estacion, Turista>> TOPmenosUtilizadas= new ArregloDinamicoGenerico<Vertex<Integer, Estacion, Turista>>(3);

	// -----------------------------------------------------------------
	// Constructor
	// -----------------------------------------------------------------

	/**
	 * Crea la clase que representa el sistema de bicicletas CitiBike. 
	 */
	public CitiBike(){
		DiGraph = new DiGraph<Integer, Estacion, Turista>();
		data = "None";
	}


	// -----------------------------------------------------------------
	// Métodos
	// -----------------------------------------------------------------

	/**
	 * Establece el archivo que el usario desea revisar. 
	 * @param int data. número de archivo a cargar.
	 */
	public void establecerFuenteDatos( int data ){
		switch(data){
		case 1:
			this.data = "PRIMER_MES";
			break;

		case 2:
			this.data = "PRIMER_MES,SEGUNDO_MES";
			break;

		case 3:
			this.data = "PRIMER_MES,SEGUNDO_MES,TERCER_MES";
			break;

		case 4:
			this.data = "PRIMER_MES,SEGUNDO_MES,TERCER_MES,CUARTO_MES";
		}
	}

	/**
	 * Establece el archivo en el bufferReader.
	 * @param Reader reader. Objeto Reader para leer.
	 */
	private Reader reader(Reader reader, String data)
	{
		try{
			switch(data){
			case "PRIMER_MES":
				reader = Files.newBufferedReader(Paths.get(PRIMER_MES));
				break;

			case "SEGUNDO_MES":
				reader = Files.newBufferedReader(Paths.get(SEGUNDO_MES));
				break;

			case "TERCER_MES":
				reader = Files.newBufferedReader(Paths.get(TERCER_MES));
				break;

			case "CUARTO_MES":
				reader = Files.newBufferedReader(Paths.get(CUARTO_MES));
				break;
			}
		}
		catch(Exception ex){
			ex.printStackTrace();
		}
		return reader;
	}

	/**
	 * Se encarga de cargar los datos a la estructura de datos de grafo dirigido.
	 * @throws CsvValidationException Lanza excepción si se presenta un problema al leer el archivo CSV.
	 * @return Retorna el número de viajes leídos de los archivos.
	 * Retorna el número total de estaciones (vértices).
	 * Retorna el número total de arcos entre estaciones (arcos totales del grafo).
	 * Retorna el arco con peso mínimo, los vértices que conecta y su valor de peso.
	 * Retorna el arco con el peso máximo, los vértices que conecta y su valor de peso.
	 */
	public String cargarDatosDiGraph( ) throws CsvValidationException{
		long now = 0;
		long start = System.currentTimeMillis();
		String[ ] data = this.data.split(",");
		int load = 0;
		int numViajes = 0; 

		while( load<data.length ){

			try{
				//Create csv reader
				Reader reader =null; 
				reader = reader(reader, data[load]);

				CSVParser parser = new CSVParserBuilder()
						.withSeparator(',')
						.build();

				CSVReader csvReader = new CSVReaderBuilder(reader)
						.withSkipLines(1)
						.withCSVParser(parser)
						.build();

				//read one record at a time.
				String[] line = csvReader.readNext();

				while( line != null){
					double tripDuration = Double.parseDouble( line[0]);

					int stationSource = Integer.parseInt(line[3]);
					String nameStationS = line[4];
					double sourceLatitud = Double.parseDouble(line[5]);
					double sourceLongitud = Double.parseDouble(line[6]);

					int stationDest = Integer.parseInt(line[7]);
					String nameStationD = line[8];
					double destLatitud = Double.parseDouble(line[9]);
					double destLongitud = Double.parseDouble(line[10]);

					String startTime = line[1];
					String stopTime = line[2];
					int id = Integer.parseInt(line[11]);
					String membresia = line[12];
					int edad = 2018 - Integer.parseInt(line[13]); 

					Estacion source = new Estacion(nameStationS, sourceLatitud, sourceLongitud, edad);
					Estacion dest = new Estacion(nameStationD, destLatitud, destLongitud, edad);
					Turista turista = new Turista(startTime, stopTime, edad, membresia, id,1);

					DiGraph.insertVertex(stationSource, source);
					DiGraph.insertVertex(stationDest, dest);

					Edge<Integer, Estacion, Turista> edge = DiGraph.getEdge(stationSource, stationDest);
					if(edge != null){
						Vertex<Integer, Estacion, Turista> s = DiGraph.getVertex(stationSource);
						Vertex<Integer, Estacion, Turista> d = DiGraph.getVertex(stationSource);
						Edge<Integer, Estacion, Turista> copy = new Edge<Integer, Estacion, Turista>(s, d, tripDuration, turista);
						s.addEddge(copy);
						d.addEddge(copy);
						int times = edge.getInfo().getTimes();
						edge.getInfo().setTimes( times+1);
						edge.setWeight( ( (edge.getWeight()*times)+tripDuration) /edge.getInfo().getTimes() );
						DiGraph.setMaxMinWeight(edge);
					}
					else{
						DiGraph.addEdge(stationSource, stationDest, tripDuration, turista);
					}					
					line = csvReader.readNext();
					numViajes++;
				}
				csvReader.close();
				reader.close();
				load++;	
			}
			catch(IOException e) {e.printStackTrace();}
		}
		now = System.currentTimeMillis();
		return toStringDiGraph( now-start, numViajes);
	}

	/**
	 * Informa el grado de entrada y salida para una estación de bicicletas.
	 * @param int iD. ID de la estación de bicibletas.
	 * @return Retorna la información del grado de entrada y de salida de la estación de bicicletas.
	 */
	public String consultarGradoEntradaSalida(int iD) {
		if(DiGraph.containsVertex(iD)){
			int gradoEntrada = DiGraph.indegree(iD);
			int gradoSalida = DiGraph.outdegree(iD);
			return "\nGrado de entrada: " + Integer.toString(gradoEntrada)
			+ "\nGrado de salida: " + Integer.toString(gradoSalida);
		}
		else return "La identificación de la estación no existe en el sistema.";
	}

	/**
	 * Calcula el total de clusters presentes en el grafo.
	 * Informa si dos identificaciones de dos estaciones pertenecen o no al mismo clúster.
	 * @param int iDOne. Primera ID  de una estación de bicicletas.
	 * @param int iDTwo. Segunda ID de una estación de bicicletas.
	 * @return Retorna el total de clusters presentes en el grafo.
	 * Retorna si las dos estaciones pertenecen o no al mismo cluster
	 */
	public String cantidadDeClustersDeViajes(int iDOne, int iDTwo){
		String res = " no se encuentran";
		KosarajuSCC<Integer, Estacion, Turista> kosarajuSCC = new KosarajuSCC<Integer, Estacion, Turista>(DiGraph);
		Vertex<Integer, Estacion, Turista> one = DiGraph.getVertex(iDOne);
		Vertex<Integer, Estacion, Turista> two = DiGraph.getVertex(iDTwo);
		if(kosarajuSCC.stronglyConnected(one, two)) res = " se encuentra";

		return "El total de clusters es: " + kosarajuSCC.count() + 
				"\nLa estación de ID " + iDOne + res + " en el mismo clúster con la estación de ID " + iDTwo+ "\n";	
	}

	/**
	 * Informa sobre el número de rutas circulares existentes que se puede realizar en un rango de tiempo.
	 * @param int timeOne. Límite inferior del tiempo dado.
	 * @param int timeTwo. Límite superior del tiempo dado.
	 * @param int iD. ID de la estación de bicibletas.
	 * @return Retorna el número de rutas circulares encontradas.
	 * Retorna la impresión de las rutas circulares disponibles.
	 * Retorna nombre de estación inicial, final y duración estimada por cada segmento de ruta.
	 */
	public String rutaTuristicaCircular(int timeOne, int timeTwo, int iD) {
		return "Incompleto";
	}

	/**
	 * Informa sobre las estaciones de llegada TOP, de salida TOP y las estaciones menos utilizadas.
	 * @return Los nomres de las 3 estaciones Top de llegada.
	 * Los nombrs de las 3 estaciones Top de salida.
	 * Los nombres de las 3 estaciones menos utilizadas.
	 */
	public String estacionesCriticas() {
		for(int i =0; i<DiGraph.vertex().size(); i++){
			actualizarTop(DiGraph.vertex().getElemento(i+1));
		}
		return "\nTOP 3 de estaciones a las que más bicicletas llegan:" +
		"\n1. " + TOPmayorLlegada.getElemento(2).getValue().getNombre()+ " - Valor: " + TOPmayorLlegada.getElemento(2).inDegree() +
		"\n2. " + TOPmayorLlegada.getElemento(3).getValue().getNombre()+ " - Valor: " + TOPmayorLlegada.getElemento(3).inDegree() + 
		"\n3. " + TOPmayorLlegada.getElemento(1).getValue().getNombre()+ " - Valor: " + TOPmayorLlegada.getElemento(1).inDegree() +
		"\n\nTOP 3 de estaciones de las que más viajes salen:" + 
		"\n1. " + TOPmayorSalida.getElemento(1).getValue().getNombre()+ " - Valor: " + TOPmayorSalida.getElemento(1).outDegree() +
		"\n2. " + TOPmayorSalida.getElemento(2).getValue().getNombre()+ " - Valor: " + TOPmayorSalida.getElemento(2).outDegree() +
		"\n3. " + TOPmayorSalida.getElemento(3).getValue().getNombre()+ " - Valor: " + TOPmayorSalida.getElemento(3).outDegree() +
		"\n\nTOP 3 de estaciones menos usadas:" + 
		"\n1. " + TOPmenosUtilizadas.getElemento(1).getValue().getNombre()+ " - Valor: " + (TOPmenosUtilizadas.getElemento(1).inDegree() + TOPmenosUtilizadas.getElemento(1).outDegree()) + 
		"\n2. " + TOPmenosUtilizadas.getElemento(2).getValue().getNombre() + " - Valor: " + (TOPmenosUtilizadas.getElemento(2).inDegree() + TOPmenosUtilizadas.getElemento(1).outDegree()) +
		"\n3. " + TOPmenosUtilizadas.getElemento(3).getValue().getNombre() + " - Valor: " + (TOPmenosUtilizadas.getElemento(3).inDegree() + TOPmenosUtilizadas.getElemento(1).outDegree());
	}

	/**
	 * Actualiza las información estaciones de llegada TOP, de salida TOP y las estaciones menos utilizadas.
	 * @param Vertex<Integer, String, Integer> v. Vertice a ser actualizado.
	 */
	public void actualizarTop(Vertex<Integer, Estacion, Turista> v){
		int llegada = Integer.MAX_VALUE, salida = Integer.MAX_VALUE, menos = 0; 
		int indice1=0, indice2=0, indice3=0;
		Vertex<Integer, Estacion, Turista> tempLlegada = null;
		Vertex<Integer, Estacion, Turista> tempSalida = null;
		Vertex<Integer, Estacion, Turista> tempMenos = null;

		boolean cent = false;

		for(int i = 0; i < 3 && !cent; i++){
			Vertex<Integer, Estacion, Turista> temp = TOPmayorLlegada.getElemento(i+1);
			Vertex<Integer, Estacion, Turista> temp2 = TOPmayorSalida.getElemento(i+1);
			Vertex<Integer, Estacion, Turista> temp3 = TOPmenosUtilizadas.getElemento(i+1);
			if(temp != null){
				if(temp.edgesIn().size()<llegada){
					tempLlegada = temp; 
					llegada = temp.edgesIn().size();
					indice1 = i+1;
				}
			}

			else {
				TOPmayorLlegada.insertElement(v, i+1);
				cent = true;
				tempLlegada = null;
			}

			if(temp2 != null){
				if(temp2.edgesOut().size()<salida){
					tempSalida = temp2; 
					salida = temp2.edgesOut().size();
					indice2 = i+1;
				}
			}

			else {
				TOPmayorSalida.insertElement(v, i+1);
				cent = true;
				tempSalida = null;
			}

			if(temp3 != null){
				if(temp3.edgesIn().size()+temp3.edgesOut().size()>menos){
					tempMenos = temp3; 
					menos = temp3.edgesOut().size() + temp3.edgesIn().size();
					indice3 = i+1;
				}
			}

			else {
				TOPmenosUtilizadas.insertElement(v, i+1);
				cent = true;
				tempMenos = null;
			}
		}

		if(tempLlegada != null) {if(v.edgesIn().size()>llegada){TOPmayorLlegada.changeInfo(indice1, v);}}
		if(tempSalida != null) {if(v.edgesOut().size()>salida){TOPmayorSalida.changeInfo(indice2, v);}}
		if(tempMenos != null ) {if(v.edgesIn().size()+v.edgesOut().size()<menos){TOPmenosUtilizadas.changeInfo(indice3, v);}}
	}

	/**
	 * Informa sobre las rutas que puede hacer el turista desde la estación de salida junto con la información de cada segmento de ruta.
	 * @param int timeResist. Tiempo máximo de resistencia en minutos.
	 * @param int iD. ID de la estación de bicibletas.
	 * @return Retorna las rutas que puede hacer el turista desde la estación de salida.
	 * Retorna nombre de estación inicial, final y duración estimada por cada segmento de ruta.
	 */
	public String rutaTuristicaPorResistencia(int timeResist, int iD) {
		return "Incompleto";
	}

	/**
	 * Informa al turista la estación desde la cual las personas en dicho rango de edad incian más viajes.
	 * @param String rangoEdad. Rango de edad del turista.
	 * @return Retorna la ruta de inicio, final y la lista de estaciones en la ruta.
	 */
	public String recomendadorDeRutas(String rangoEdad) {
		Vertex<Integer, Estacion, Turista> inicio = null, fin = null; 
		switch(rangoEdad){
		case "0-10":
			inicio = inicio(0,10);
			fin = fin(0,10);
			break;

		case "11-20":
			inicio = inicio(11,20);
			fin = fin(11,20);
			break;

		case "21-30":
			inicio = inicio(21,30);
			fin = fin(21,30);
			break;

		case "31-40":
			inicio = inicio(31,40);
			fin = fin(31,40);
			break;

		case "41-50":
			inicio = inicio(41,50);
			fin = fin(41,50);
			break;

		case "51-60":
			inicio = inicio(51,60);
			fin = fin(51,60);
			break;

		case "60+":
			inicio = inicio(60,Integer.MAX_VALUE);
			fin = fin(60,Integer.MAX_VALUE);
			break;
		}

		if(inicio!=null && fin != null){
			DijkstraSP<Integer, Estacion, Turista> dijkstraSP = new DijkstraSP<Integer, Estacion, Turista>(DiGraph, inicio);
			ArregloDinamicoGenerico<Edge<Integer, Estacion, Turista>> minPath = dijkstraSP.minPath(inicio, fin);

			String lista = "\n"; 
			for(int i = 0; i<minPath.size(); i++){
				lista+= "\n"+Integer.toString(i+1)+". "+minPath.getElemento(i+1).getDest().getValue().getNombre();
			}

			return "\n Estación en el rango de edad " + rangoEdad + " donde inician más viajes: " + inicio.getValue().getNombre() + 
					"\n Estación en el rango de edad " + rangoEdad + " donde terminan más viajes: " + fin.getValue().getNombre() + 
					lista;
		}
		else
			return "No se encontraron estaciones con el rango de edad dado";
	}

	public Vertex<Integer, Estacion, Turista> inicio(int inferior, int superior)
	{
		Vertex<Integer, Estacion, Turista> res = null; 
		int contador = 0; 
		for(int i =0; i<DiGraph.vertex().size(); i++){
			int contadorTemp = 0; 
			ArregloDinamicoGenerico<Edge<Integer, Estacion, Turista>> temp = DiGraph.vertex().getElemento(i+1).edgesIn(); 
			for(int j = 0; j<temp.size(); j++){
				int edadTemp = temp.getElemento(j+1).getInfo().getEdad();
				if(edadTemp>inferior && edadTemp<superior){contadorTemp++;}
			}
			if (contadorTemp>contador) 
			{
				contador = contadorTemp;
				res = DiGraph.vertex().getElemento(i+1);
			}
		}
		return res;
	}

	public Vertex<Integer, Estacion, Turista> fin(int inferior, int superior)
	{
		Vertex<Integer, Estacion, Turista> res = null; 
		int contador = 0; 
		for(int i =0; i<DiGraph.vertex().size(); i++){
			int contadorTemp = 0; 
			ArregloDinamicoGenerico<Edge<Integer, Estacion, Turista>> temp = DiGraph.vertex().getElemento(i+1).edgesOut(); 
			for(int j = 0; j<temp.size(); j++){
				int edadTemp = temp.getElemento(j+1).getInfo().getEdad();
				if(edadTemp>inferior && edadTemp<superior){contadorTemp++;}			
			}
			if (contadorTemp>contador) 
			{
				contador = contadorTemp;
				res = DiGraph.vertex().getElemento(i+1);
			}
		}
		return res; 
	}

	/**
	 * Informa al turista la estación de inicio más cercana a su localización.
	 * Informa la estación final más cercana al punto de interés al que desea llegar.
	 * Indica la ruta con menor tiempo para llegar al destino.
	 * @param Double latAct. Latitud del punto de ubicación actual.
	 * @param Double lonAct. Longitud del punto de ubicación actual. 
	 * @param Double latSit. Latitud del sitio turístico de destino.
	 * @param Double lonSit. Longitud del sitio turístico de destino. 
	 * @return Retorna la estación más cercana al sitio turístico que el turista quiere visitar.
	 * Retorna la estación más cercana al sitio turistico que el turista quiere visitar.
	 * Retorna el tiempo estimado de viaje.
	 * Retorna la lista de estacione en la ruta.
	 */
	public String rutaDeInteresTuristico(Double latAct, Double lonAct, Double latSit, Double lonSit) {
		Vertex<Integer, Estacion, Turista> source = null, dest=null, sourceCerca = null, destCerca = null;
		for(int i = 0; i < DiGraph.vertex().size(); i++){
			Vertex<Integer, Estacion, Turista> temp = DiGraph.vertex().getElemento(i+1);
			if(temp.getValue().getLatitud()==latAct && temp.getValue().getLongitud()==lonAct){
				source = temp; 
			}
			else if(temp.getValue().getLatitud()==latSit && temp.getValue().getLongitud()==lonSit)
			{
				dest = temp;
			}
		}
		if(source != null && dest!=null){
			ArregloDinamicoGenerico<Edge<Integer, Estacion, Turista>> adjSource = source.edgesOut();
			double sourceCercano = Integer.MAX_VALUE;
			for( int i = 0; i<adjSource.size(); i++){
				Vertex<Integer, Estacion, Turista> destTemp1 = adjSource.getElemento(i+1).getDest();
				double temp1 = geoDistance(source.getValue().getLatitud(), source.getValue().getLongitud(), destTemp1.getValue().getLatitud(), destTemp1.getValue().getLongitud());
				if(temp1<sourceCercano){
					sourceCerca = destTemp1;
				}
			}

			ArregloDinamicoGenerico<Edge<Integer, Estacion, Turista>> adjDest = dest.edgesOut();
			double destCercano = Integer.MAX_VALUE;
			for( int i = 0; i<adjDest.size(); i++){
				Vertex<Integer, Estacion, Turista> destTemp2 = adjDest.getElemento(i+1).getDest();
				double temp2 = geoDistance(dest.getValue().getLatitud(), dest.getValue().getLongitud(), destTemp2.getValue().getLatitud(), destTemp2.getValue().getLongitud());
				if(temp2<destCercano){
					destCerca = destTemp2;
				}
			}
			String nombreDestCerca, nombreSourceCerca;
			if(sourceCerca!=null) nombreSourceCerca = sourceCerca.getValue().getNombre();
			else nombreSourceCerca = "No existe";

			if(destCerca!=null) nombreDestCerca = destCerca.getValue().getNombre();
			else nombreDestCerca = "No existe";

			DijkstraSP<Integer, Estacion, Turista> dijkstraSP = new DijkstraSP<Integer, Estacion, Turista>(DiGraph, source);
			ArregloDinamicoGenerico<Edge<Integer, Estacion, Turista>> minPath = dijkstraSP.minPath(source, dest);

			String lista = "\n"; 
			for(int i = 0; i<minPath.size(); i++){
				lista+= "\n"+Integer.toString(i+1)+". "+minPath.getElemento(i+1).getDest().getValue().getNombre();
			}

			return "\nLista de estaciones en la ruta mínima" + lista +
					"\n\nTiempo estimado de viaje: " + minPath.lastElement().getWeight() + 
					"\nEstación más cercana a la ubicación origen: " +  nombreSourceCerca +
					"\nEstación más cercana al sitio turístico: " + nombreDestCerca + "\n";
		}
		else 
			return "Los valores ingresado no son los correctos.";

	}

	/**
	 * Se encarga de calcular una distancia según la formula de Hess.
	 * @param double latitudBase. Latitud de una estación base. 
	 * @param double longitudBase. longitud de una estación base.
	 * @param double latitudRel. Latitud de una estación de referencia. 
	 * @param double longitudRel. Longitud de una estación de referencia. 
	 * @return
	 */
	public static double geoDistance(double latitudBase , double longitudBase, double latitudRel , double longitudRel){
		double latBase = Math.toRadians(latitudBase);
		double lonBase = Math.toRadians(longitudBase);
		double latRel = Math.toRadians(latitudRel);
		double lonRel = Math.toRadians(longitudRel);

		double distLongitud = lonRel - lonBase;
		double distLatitud = latRel - latBase;
		double a = Math.pow(Math.sin(distLatitud / 2), 2) + Math.cos(latBase) * Math.cos(latRel) * Math.pow(Math.sin(distLongitud/ 2),2);
		double b = 2 * Math.asin(Math.sqrt(a));
		return b*6371;
	}


	/**
	 * Indica cuál es el par de estaciones adyacentes (inicio y llegada) que las personas de ese grupo de edad, con suscripción de 3 días más realizan.
	 * @param String rangoEdad. Rango de edad del grupo dde turistas.
	 * @return Retorna las estaciones adyacentes que más utilizan las personas del grupo de edad.
	 * Retorna el total de viajes registrados en el sistemma.
	 * Retorna las parejas de las estaciones que cumplen la condición.
	 */
	public String identificacionDeEstacionesParaPublicidad(String rangoEdad) {
		return "Incompleto";
	}

	/**
	 * Informa todas las estaciones por las que la bibicleta ha pasado.
	 * Calcula el tiempo total de uso y el tiempo total de estacionada.
	 * @param int iD. ID de la estación de bicibletas.
	 * @param String fech. Fecha a consultar.
	 * @return Retorna la lista de estaciones por las que ha pasado la bicicleta.
	 * Retorna el tiempo total de uso y el tiempo total de estacionada.
	 */
	public String identificacionDeEstacionesParaMantenimiento(int iD, String fech) throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		double tiempoDeUso = 0;
		for (int j = 0; j < DiGraph.edges().size(); j++) {
			String sinComillas = DiGraph.edges().getElemento(j+1).getInfo().getStartTime().replace("\"", "");
			Date fechaInicio = sdf.parse( sinComillas);
			Date fechaComparacion =sdf.parse(fech);
			if (DiGraph.edges().getElemento(j+1).getInfo().getId()==iD) {
				if (fechaInicio.equals(fechaComparacion)) {
					String sinComillasFechaInicio=DiGraph.edges().getElemento(j+1).getInfo().getStartTime().replace("\"", "");
					String sinComillasFechaFinal=DiGraph.edges().getElemento(j+1).getInfo().getStopTime().replace("\"", "");
					//////////////////////
					String horaInicio = sinComillasFechaInicio.split(" ")[1];
					double horaInicioNumero=Double.parseDouble(horaInicio.split(":")[0])*3600;
					double minutoInicioNumero=Double.parseDouble(horaInicio.split(":")[1])*60;
					double segundoInicioNumero=Double.parseDouble(horaInicio.split(":")[2]);
					double totalInicio=horaInicioNumero+minutoInicioNumero+segundoInicioNumero;
					///////////////
					String horaFin = sinComillasFechaFinal.split(" ")[1];
					double horaFinalNumero=Double.parseDouble(horaFin.split(":")[0])*3600;
					double minutoFinalNumero=Double.parseDouble(horaFin.split(":")[1])*60;
					double segundoFinalNumero=Double.parseDouble(horaFin.split(":")[2]);
					double totalFinal=horaFinalNumero+minutoFinalNumero+segundoFinalNumero;
					///////////////////
					double Total=totalFinal-totalInicio;
					tiempoDeUso += Total;
				}
			}	
		}

		double tiempoEsta=86400-tiempoDeUso;
		return "el tiempo total de uso es de:"+tiempoDeUso+" "+" y el tiempo que estuvo estacionado es de"+ tiempoEsta;
	}
	public String identificacionDeEstacionesParaMantenimiento2(int iD, String fech) throws ParseException {
		for (int i = 0; i < DiGraph.vertex().size(); i++) {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			for (int j = 0; j < DiGraph.vertex().getElemento(i+1).edgesIn().size(); j++) {
				String sinComillas = DiGraph.vertex().getElemento(i+1).edgesOut().getElemento(j+1).getInfo().getStartTime().replace("\"", "");
				Date fechaInicio = sdf.parse( sinComillas);
				Date fechaComparacion =sdf.parse(fech);
				if (DiGraph.vertex().getElemento(i+1).edgesOut().getElemento(j+1).getInfo().getId()==iD && fechaInicio.equals(fechaComparacion)) {
					System.out.println("estaciones por las que ha pasado:");
					System.out.println(DiGraph.vertex().getElemento(i+1).getValue().getNombre());
				}

			}
		}
		return "";
	}
	/**
	 * Se encarga de realizar el reporte al momento de cargar los datos RBT.
	 * @param long time. El tiempo en el que se realizó la carga de datos.
	 * @param int numViajes. El número de viajes que se concretó al momento de realizar la carga de datos.
	 * @return Retorna el número de viajes leídos de los archivos.
	 * Retorna el número total de estaciones (vértices).
	 * Retorna el número total de arcos entre estaciones (arcos totales del grafo).
	 * Retorna el arco con peso mínimo, los vértices que conecta y su valor de peso.
	 * Retorna el arco con el peso máximo, los vértices que conecta y su valor de peso.
	 */
	private String toStringDiGraph( long time, int numViajes ){
		ArregloDinamicoGenerico< Vertex<Integer, Estacion, Turista>> estaciones = DiGraph.vertex();
		ArregloDinamicoGenerico< Edge<Integer, Estacion, Turista>> trayectorias = DiGraph.edges();
		Edge<Integer, Estacion, Turista> minWeight = DiGraph.minWeight(); 
		Edge<Integer, Estacion, Turista> maxWeight = DiGraph.maxWeight();

		return SEPARADOR +" REPORTE " +SEPARADOR
				+"\n\nNúmero de viajes leídos de los archivos: " + numViajes
				+ "\nNúmero total de estaciones (vértices): " + estaciones.size()
				+ "\nNúmero total de arcos entre estaciones (arcos totales del grafo): " + trayectorias.size()
				+ "\n\nArco con el peso mínimo: \nId de estación de origen: " + minWeight.getSource().getId() + " - Nombre: " + minWeight.getSource().getValue().getNombre()
				+ "\nId de estación de destino: " + minWeight.getDest().getId() + " - Nombre: "+ minWeight.getDest().getValue() .getNombre()
				+ "\nPeso: "+ minWeight.getWeight()
				+ "\n\nArco con el peso máximo: \nId de estación de origen: " + maxWeight.getSource().getId() + " - Nombre: " + maxWeight.getSource().getValue().getNombre()
				+ "\nId de estación de destino: " + maxWeight.getDest().getId() + " - Nombre: "+ maxWeight.getDest().getValue() .getNombre()
				+ "\nPeso: "+ maxWeight.getWeight()				
				+ "\n\nTiempo de ejecución: " + (time) + "mseg\n";
	}

}
