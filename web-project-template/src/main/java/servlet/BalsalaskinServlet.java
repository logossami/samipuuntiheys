package servlet;

import java.io.IOException;
import java.text.DecimalFormat;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import database.JDBCBalsalevyDAO;
import model.Balsalevy;

@SuppressWarnings("serial")
@WebServlet("/tiheyslaskin")
public class BalsalaskinServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		if (req.getParameter("action") != null && req.getParameter("action").equals("Laske")) {

			String tulos = "";
			String tulosPaunoina = "";
			DecimalFormat desimaali = new DecimalFormat("0.00");

			String stringThickness = req.getParameter("paksuus");
			String stringLength = req.getParameter("pituus");
			String stringWidth = req.getParameter("leveys");
			String stringWeight = req.getParameter("paino");

			req.setAttribute("paksuus", stringThickness);
			req.setAttribute("pituus", stringLength);
			req.setAttribute("leveys", stringWidth);
			req.setAttribute("paino", stringWeight);

			try {

				Double paksuus = Double.parseDouble(req.getParameter("paksuus")) / 1000;
				Double pituus = Double.parseDouble(req.getParameter("pituus")) / 1000;
				Double leveys = Double.parseDouble(req.getParameter("leveys")) / 1000;
				Double paino = Double.parseDouble(req.getParameter("paino")) / 1000;

				tulosPaunoina = desimaali.format(
						paino * 2.20462262185 / ((paksuus * 3.2808399) * (pituus * 3.2808399) * (leveys * 3.2808399)));
				tulos = desimaali.format(paino / (paksuus * pituus * leveys));

			} catch (Exception e) {
				e.printStackTrace();
			}

			req.setAttribute("tiheysKG", tulos);
			req.setAttribute("tiheysLB", tulosPaunoina);
		}

		req.getRequestDispatcher("/WEB-INF/index.jsp").forward(req, resp);

	}

	// doPost-metodin toteutusta on aukikirjoitettu tässä, vaikka itse toiminnallisuutta ei nyt ole saatavilla.
	// Metodi käsittelisi lomakkeelta saatua tietoa, minkä avulla muutettaisiin tietokannan sisältöä.
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		double paksuus = Double.parseDouble(req.getParameter("Paksuus"));
		double pituus = Double.parseDouble(req.getParameter("Pituus"));
		double leveys = Double.parseDouble(req.getParameter("Leveys"));
		double paino = Double.parseDouble(req.getParameter("Paino"));

		JDBCBalsalevyDAO dao = new JDBCBalsalevyDAO();
		double tiheys = dao.tiheys(paino, leveys, paksuus, pituus);
		Balsalevy uusiLevy = new Balsalevy(paksuus, pituus, leveys, paino, tiheys, null);

		dao.addItem(uusiLevy);

		resp.sendRedirect("/tiheyslaskin");
	}
}