package util;

import java.awt.Dimension;

import javax.swing.JFrame;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.function.Function2D;
import org.jfree.data.function.NormalDistributionFunction2D;
import org.jfree.data.general.DatasetUtilities;
import org.jfree.data.xy.XYDataset;

import studentsmonitoring.Klasa;
import studentsmonitoring.Ocena;

/**
 * Klasa zajmuje się tworzeniem wykresu rozkładu normalnego
 */
public class WykresRozkladuNormalnego extends JFrame {
	private static final long serialVersionUID = 1L;

	/**
	 * Konstruktor tworzący wykres rozkładu normalnego
	 * 
	 * @param klasa
	 *            obiekt klasy
	 * @param przedmiot
	 *            wybrany przedmiot
	 */
	public WykresRozkladuNormalnego(Klasa klasa, Ocena.Przedmiot przedmiot) {
		super("StudentsMonitoring");
		double srednia = klasa.obliczSredniaKlasy(przedmiot);
		double odchylenieStandardowe = klasa.obliczOdchylenieStandardoweKlasy(przedmiot);

		Function2D funkcja = new NormalDistributionFunction2D(srednia, odchylenieStandardowe);
		XYDataset zbiorDanych = DatasetUtilities.sampleFunction2D(funkcja, 1.0, 6.0, 100, "Krzywa dzwonowa");
		JFreeChart wykres = ChartFactory.createXYLineChart(
				"Rozkład normalny ocen z przedmiotu:  " + przedmiot.getNazwa().toUpperCase(), "Ocena",
				"Prawdopodobieństwo", zbiorDanych, PlotOrientation.VERTICAL, true, true, false);

		setContentPane(new ChartPanel(wykres));
		setSize(new Dimension(700, 500));
		setLocationRelativeTo(null);
		setAlwaysOnTop(true);
		setVisible(true);
	}
}