package negocio.reservas;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class IntervaloTiempo implements Comparable<IntervaloTiempo> {
	private Date ini, fin;
	public static final String dateFormat = "dd/MM/yyyy";
	
	public IntervaloTiempo(Date ini, Date fin) {
		if(ini == null || fin == null || ini.compareTo(fin) > 0) throw new IllegalArgumentException("Fechas invalidas");
		this.ini = ini;
		this.fin = fin;
	}
	
	public Date getIni() {return new Date(ini.getTime());}
	
	public Date getFin() {return new Date(fin.getTime());}
	
	public int getDaysInBetween() {
		return (int) TimeUnit.DAYS.convert(fin.getTime() - ini.getTime(), TimeUnit.MILLISECONDS) + 1;
	}
	
	boolean isInInterval(Date d) {
		if(d.compareTo(ini) == 0 || d.compareTo(fin) == 0) return true;
		if(d.compareTo(fin) < 0 && d.compareTo(ini) > 0) return true;
		return false;
	}

	@Override
	public int compareTo(IntervaloTiempo other) {
		if(other.ini.compareTo(ini) < 0 && other.fin.compareTo(ini) < 0) return 1;
		if(fin.compareTo(other.ini) < 0 && fin.compareTo(other.fin) < 0) return -1;
		return 0;
	}

	@Override
	public String toString() {
		SimpleDateFormat formater = new SimpleDateFormat(dateFormat);
		return formater.format(ini) + "-" + formater.format(fin);
	}
	
	public static IntervaloTiempo parseInterval(String ini, String fin) {
		SimpleDateFormat formater = new SimpleDateFormat(dateFormat);
		try { 
			return new IntervaloTiempo(formater.parse(ini), formater.parse(fin));
		} catch (ParseException | IllegalArgumentException e) {
			return null;
		}
	}
	
	
	public static String formatDate(Date d) {
		if(d == null) return new String();
		SimpleDateFormat formater = new SimpleDateFormat(dateFormat);
		return formater.format(d);
	}
	
	public static Date parseDate(String str) {
		SimpleDateFormat formater = new SimpleDateFormat(dateFormat);
		try {
			return formater.parse(str);
		} catch (ParseException e) {
			return null;
		}
	}
	
	public static Date addMonthToDate(Date d) {
		if(d == null) return null;
		Calendar Cal = Calendar.getInstance();
	    Cal.setTime(d);    
	    Cal.add(Calendar.MONTH, 1);    
	    return Cal.getTime();
	}
	
	public static Date addDayToDate(Date d) {
		if(d == null) return null;
		Calendar Cal = Calendar.getInstance();
	    Cal.setTime(d);    
	    Cal.add(Calendar.DATE, 1);    
	    return Cal.getTime();
	}
	
}
