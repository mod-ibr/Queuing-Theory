package queuesystemproj.Deterministic;

import java.awt.Color;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import javax.swing.WindowConstants;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.XYPlot;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

/**
 * @author imssbora
 *
 */
public class XYStepChartExample extends JFrame {

    private static final long serialVersionUID = 1L;

    public XYStepChartExample(String title, DD1K_abstract D) {
        super(title);
        // Create dataset
        XYDataset dataset = createDataset(D);
        // Create chart
        JFreeChart chart = ChartFactory.createXYStepChart(
                "Deterministic", // Chart title
                "X-Axis", // X-Axis Label
                "Y-Axis", // Y-Axis Label
                dataset
        );

        XYPlot plot = (XYPlot) chart.getPlot();
        plot.setBackgroundPaint(new Color(229, 150, 97, 60));

        ChartPanel panel = new ChartPanel(chart);
        setContentPane(panel);
    }

    private XYDataset createDataset(DD1K_abstract D) {
        XYSeriesCollection dataset = new XYSeriesCollection();

        XYSeries series1 = new XYSeries("Series1");

        int TiStep = D.getTi() / D.getGCM();
        System.out.println("D instance of : " + (D instanceof DD1K_LampdaGreater));
        //  System.out.println("lambda In D : "+D.getLampda() +" , Mu In D : " + D.getMu() );
        if (D instanceof DD1K_LampdaGreater) {
            for (int i = 0; i <= D.lengthOfPlotSegments; i++) {
                series1.add(i, D.seriesDotsToPlot(i)); 
                // System.out.println("Num Of Cust = " + D.calculateCustomersAtTimeT(i) +"  At time : "+i);
                int Cycle, c = i;
                c -= D.getTi();
                Cycle = c / (int) (D.getGCM() / D.getLampda());

                if (i >= D.getTi()) {
                    if (i < D.getTi() + Cycle * (D.getGCM() / D.getLampda()) + D.getGCM()) {
                        series1.add(i, D.calculateCustomersAtTimeT(i));
                    } else if (i == D.getTi() + Cycle * (D.getGCM() / D.getLampda()) + D.getGCM()) {
                        series1.add(i, D.calculateCustomersAtTimeT(i) - 1);
                    } else if (i > D.getTi() + Cycle * (D.getGCM() / D.getLampda()) + D.getGCM()) {
                        series1.add(i, D.calculateCustomersAtTimeT(i));
                    }
                }
            }

//            int Cycle, c = i;       // 47 , 48
//            c -= D.getTi();         //3 ,4
//            Cycle = c / (int) (D.getGCM() *6);// 0 ,0
//
//            if (i >= D.getTi()) {// 48 >= 44 +2 && 48 < 44 + 4
//                if ((i >= D.getTi() + Cycle * (D.getGCM() *6) + D.getGCM()) && (i < D.getTi() + Cycle * (D.getGCM() *6) + 2*D.getGCM() ) ) {
//                    series1.add(i, D.calculateCustomersAtTimeT(i) - 1);
//                } else{
//                    series1.add(i, D.calculateCustomersAtTimeT(i));
//                }
//            }
        } else {
            System.out.println("Start Chart Mu grater");
            for (int i = 0; i < 50; i++) {
                series1.add(i, D.calculateCustomersAtTimeT(i));
            }
        }

//        series1.add(1, 1);
//        series1.add(2, 2);
//        series1.add(3, 3);
//        series1.add(4, 4);
//        series1.add(5, 5);
//     XYSeries series2 = new XYSeries("Series2");
//     series2.add(5, 6);
//     series2.add(9, 5);
//     series2.add(10, 9);
//     series2.add(18, 11);
//     series2.add(15, 18);
        // Add series to dataset
        dataset.addSeries(series1);
//     dataset.addSeries(series2);
        return dataset;
    }

}
