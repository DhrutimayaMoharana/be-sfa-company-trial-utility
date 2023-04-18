package com.watsoo.sfa.trial.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;
import java.util.concurrent.TimeUnit;

import org.springframework.stereotype.Component;

import com.watsoo.sfa.trial.constant.Constant;

@Component
public class DateUtil {

	public static Date getFromDate(Long fromDate) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(new Date(fromDate));
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MILLISECOND, 0);
		Date dateFrom = cal.getTime();
		// dateFrom = addMinutesToJavaUtilDate(dateFrom,
		// -(Constant.IST_OFFSET_IN_MINUTES));
		return dateFrom;
	}

	public static Date setDate(Long fromDate) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(new Date(fromDate));
		cal.set(Calendar.HOUR_OF_DAY, 17);
		cal.set(Calendar.MINUTE, 58);
		cal.set(Calendar.SECOND, 58);
		cal.set(Calendar.MILLISECOND, 0);
		Date dateFrom = cal.getTime();
		// dateFrom = addMinutesToJavaUtilDate(dateFrom,
		// -(Constant.IST_OFFSET_IN_MINUTES));
		return dateFrom;
	}

	public static Date setDateDayStartEnd(Long fromDate) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(new Date(fromDate));
		cal.set(Calendar.HOUR_OF_DAY, 18);
		cal.set(Calendar.MINUTE, 30);
		cal.set(Calendar.SECOND, 00);
		cal.set(Calendar.MILLISECOND, 00);
		Date dateFrom = cal.getTime();
		// dateFrom = addMinutesToJavaUtilDate(dateFrom,
		// -(Constant.IST_OFFSET_IN_MINUTES));
		return dateFrom;
	}

	public static Date setwhStartDate(Long fromDate) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(new Date(fromDate));
		cal.set(Calendar.HOUR_OF_DAY, 05);
		cal.set(Calendar.MINUTE, 00);
		cal.set(Calendar.SECOND, 00);
		cal.set(Calendar.MILLISECOND, 0);
		Date dateFrom = cal.getTime();
		// dateFrom = addMinutesToJavaUtilDate(dateFrom,
		// -(Constant.IST_OFFSET_IN_MINUTES));
		return dateFrom;
	}

	public static Date setmanuwhStartDate(Long fromDate) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(new Date(fromDate));
		cal.set(Calendar.HOUR_OF_DAY, 14);
		cal.set(Calendar.MINUTE, 10);
		cal.set(Calendar.SECOND, 00);
		cal.set(Calendar.MILLISECOND, 0);
		Date dateFrom = cal.getTime();
		// dateFrom = addMinutesToJavaUtilDate(dateFrom,
		// -(Constant.IST_OFFSET_IN_MINUTES));
		return dateFrom;
	}

	public static Date setwhEndDate(Long fromDate) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(new Date(fromDate));
		cal.set(Calendar.HOUR_OF_DAY, 23);
		cal.set(Calendar.MINUTE, 00);
		cal.set(Calendar.SECOND, 00);
		cal.set(Calendar.MILLISECOND, 0);
		Date dateFrom = cal.getTime();
		// dateFrom = addMinutesToJavaUtilDate(dateFrom,
		// -(Constant.IST_OFFSET_IN_MINUTES));
		return dateFrom;
	}

	public static Date manupilate(Long fromDate, Integer date, Integer hour, Integer min, Integer sec) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(new Date(fromDate));
		cal.set(Calendar.DATE, date);
		cal.set(Calendar.HOUR_OF_DAY, hour);
		cal.set(Calendar.MINUTE, min);
		cal.set(Calendar.SECOND, sec);
		cal.set(Calendar.MILLISECOND, 0);
		Date dateFrom = cal.getTime();
		// dateFrom = addMinutesToJavaUtilDate(dateFrom,
		// -(Constant.IST_OFFSET_IN_MINUTES));
		return dateFrom;
	}

	public static Date getFromDateLong(Long fromDate) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(new Date(fromDate));
		Date dateFrom = cal.getTime();
		return dateFrom;
	}

	public static Date getFromDateWithTime(Long fromDate) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(new Date(fromDate));
		Date dateFrom = cal.getTime();
		dateFrom = cal.getTime();
		dateFrom = addMinutesToJavaUtilDate(dateFrom, -(Constant.IST_OFFSET_IN_MINUTES));
		SimpleDateFormat isoFormat = new SimpleDateFormat("E MMM dd HH:mm:ss Z yyyy");
		isoFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
		try {
			dateFrom = isoFormat.parse(dateFrom.toString());
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return dateFrom;
	}

	public static String getFromDateSqlFormat(Long fromDate) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(new Date(fromDate));
		Date dateFrom = cal.getTime();
		dateFrom = cal.getTime();
		dateFrom = addMinutesToJavaUtilDate(dateFrom, -(Constant.IST_OFFSET_IN_MINUTES));
		SimpleDateFormat isoFormat = new SimpleDateFormat("E MMM dd HH:mm:ss Z yyyy");
		isoFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
		try {
			dateFrom = isoFormat.parse(dateFrom.toString());
		} catch (ParseException e) {
			e.printStackTrace();
		}
		DateFormat df = new SimpleDateFormat(Constant.DATE_FORMAT_YYYY_MM_DD);
		return df.format(dateFrom);
	}

	public static Date getFromDateInUTCISTTime(Long fromDate) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(new Date(fromDate));
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MILLISECOND, 0);
		Date dateFrom = cal.getTime();
		dateFrom = addMinutesToJavaUtilDate(dateFrom, -(Constant.IST_OFFSET_IN_MINUTES));
		SimpleDateFormat isoFormat = new SimpleDateFormat("E MMM dd HH:mm:ss Z yyyy");
		isoFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
		try {
			dateFrom = isoFormat.parse(dateFrom.toString());
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return dateFrom;
	}

	public static Date getToDateWithTime(Long toDate) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(new Date(toDate));
		Date dateTo = calendar.getTime();
		dateTo = addMinutesToJavaUtilDate(dateTo, -(Constant.IST_OFFSET_IN_MINUTES));
		SimpleDateFormat isoFormat = new SimpleDateFormat("E MMM dd HH:mm:ss Z yyyy");
		isoFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
		try {
			dateTo = isoFormat.parse(dateTo.toString());
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return dateTo;
	}

	public static Date getToDateWithTimeV2(Long toDate) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(new Date(toDate));
		Date dateTo = calendar.getTime();
		dateTo = addMinutesToJavaUtilDate(dateTo, -(Constant.IST_OFFSET_IN_MINUTES));
		SimpleDateFormat isoFormat = new SimpleDateFormat("E MMM dd HH:mm:ss Z yyyy");
		isoFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
		try {
			dateTo = isoFormat.parse(dateTo.toString());
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return dateTo;
	}

	public static Date getToDateWithCurrentTime(Long toDate) {
		Date date = new Date();
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(new Date(toDate));
		Date dateTo = null;
		if (calendar.getTime().getDate() == date.getDate()) {
			dateTo = calendar.getTime();
		} else {
			dateTo = calendar.getTime();
			dateTo = addMinutesToJavaUtilDate(dateTo, -(Constant.IST_OFFSET_IN_MINUTES));
		}
		SimpleDateFormat isoFormat = new SimpleDateFormat("E MMM dd HH:mm:ss Z yyyy");
		isoFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
		try {
			dateTo = isoFormat.parse(dateTo.toString());
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return dateTo;
	}

	public static Date getToDate(Long toDate) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(new Date(toDate));
		calendar.set(Calendar.HOUR_OF_DAY, 23);
		calendar.set(Calendar.MINUTE, 59);
		calendar.set(Calendar.SECOND, 59);
		calendar.set(Calendar.MILLISECOND, 999);
		Date dateTo = calendar.getTime();
		// dateTo = addMinutesToJavaUtilDate(dateTo, -(Constant.IST_OFFSET_IN_MINUTES));
		return dateTo;
	}

	public static Date getToDateLong(Long toDate) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(new Date(toDate));
		Date dateTo = calendar.getTime();
		return dateTo;
	}

	public static Date convertStringToDate(String stringDate, String format) {
		Date date = null;
		try {
			date = new SimpleDateFormat(format).parse(stringDate);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return date;
	}

	public static Date getDateFromLong(Long fromDate) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(new Date(fromDate));
		Date dateFrom = cal.getTime();
		return dateFrom;
	}

	public static String localDateTimeToStringInFormatYYYYMMDD$HHMMSS(Date date) {
		if (date == null)
			return "";
		SimpleDateFormat formatter = new SimpleDateFormat(Constant.DATE_FORMAT_YYYY_MM_DD_HH_MM_SS);
		return formatter.format(date);
	}

	public static String localDateTimeToStringInFormatYYYYMMDDTHHMMSSZ(Date date) {
		if (date == null)
			return "";
		SimpleDateFormat formatter = new SimpleDateFormat(Constant.DATE_FORMAT_YYYY_MM_DD_T_HH_MM_SS_Z);
		return formatter.format(date);
	}

	public static String localDateTimeToStringInFormatYYYYMMDD(Date date) {
		if (date == null)
			return "";
		SimpleDateFormat formatter = new SimpleDateFormat(Constant.DATE_FORMAT_YYYY_MM_DD);
		return formatter.format(date);
	}

	public static String localDateTimeToStringInFormat(Date date, String format) {
		if (date == null)
			return "";
		SimpleDateFormat formatter = new SimpleDateFormat(format);
		return formatter.format(date);
	}

	public static String getFromDateWithFromat(Long fromDate) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(new Date(fromDate));
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 1);
		cal.set(Calendar.MILLISECOND, 0);
		Date dateFrom = cal.getTime();
		dateFrom = addMinutesToJavaUtilDate(dateFrom, Constant.IST_OFFSET_IN_MINUTES);
		DateFormat df = new SimpleDateFormat(Constant.DATE_FORMAT_YYYY_MM_DD_T_HH_MM_SS_Z);
		return df.format(dateFrom);
	}

	// DATE WILL BE IN UTC WITH TIME
	public static String getFuelFromDate(Long fromDate) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(new Date(fromDate));
		Date dateFrom = cal.getTime();
		dateFrom = addMinutesToJavaUtilDate(dateFrom, -Constant.IST_OFFSET_IN_MINUTES);
		DateFormat df = new SimpleDateFormat(Constant.DATE_FORMAT_YYYY_MM_DD_T_HH_MM_SS_Z);
		return df.format(dateFrom);
	}

	public static String getFromDateWithTimeFromat(Long fromDate) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(new Date(fromDate));
		Date dateFrom = cal.getTime();
		dateFrom = addMinutesToJavaUtilDate(dateFrom, -Constant.IST_OFFSET_IN_MINUTES);
		DateFormat df = new SimpleDateFormat(Constant.DATE_FORMAT_YYYY_MM_DD_T_HH_MM_SS_Z);
		return df.format(dateFrom);
	}

//	public static String getFromDateWithTimeFromatDayEnd(Long fromDate) {
//		Calendar cal = Calendar.getInstance();
//		cal.setTime(new Date(fromDate));
//		Date dateFrom = cal.getTime();
//		dateFrom = addMinutesToJavaUtilDate(dateFrom, -Constant.IST_OFFSET_IN_MINUTES);
//		dateFrom = addMinutesToJavaUtilDate(dateFrom, -Constant.ONE_HOUR_MINUTES);
//		DateFormat df = new SimpleDateFormat(Constant.DATE_FORMAT_YYYY_MM_DD_T_HH_MM_SS_Z);
//		return df.format(dateFrom);
//	}
//	
//	public static Date getFromDateWithTimeFromatDayEndDate(Long fromDate) {
//		Calendar cal = Calendar.getInstance();
//		cal.setTime(new Date(fromDate));
//		Date dateFrom = cal.getTime();
//		dateFrom = addMinutesToJavaUtilDate(dateFrom, -Constant.IST_OFFSET_IN_MINUTES);
//		dateFrom = addMinutesToJavaUtilDate(dateFrom, -Constant.ONE_HOUR_MINUTES);
//		return dateFrom;
//	}

	public static String getFromDateWithOutAddTimeFromat(Long fromDate) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(new Date(fromDate));
		Date dateFrom = cal.getTime();
		DateFormat df = new SimpleDateFormat(Constant.DATE_FORMAT_YYYY_MM_DD_T_HH_MM_SS_Z);
		return df.format(dateFrom);
	}

	public static String getFromDateWithOutAddTimeFromatForChaEstival(Long fromDate) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(new Date(fromDate));
		Date dateFrom = cal.getTime();
		dateFrom = addMinutesToJavaUtilDate(dateFrom, -Constant.IST_OFFSET_IN_MINUTES);
		DateFormat df = new SimpleDateFormat(Constant.DATE_FORMAT_YYYY_MM_DD_T_HH_MM_SS_Z);
		return df.format(dateFrom);
	}

	public static String getToDateWithFromat(Long toDate) {
		Date date = new Date();
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(new Date(toDate));
		Date dateTo = null;
		if (calendar.getTime().getDate() == date.getDate()) {
			calendar.set(Calendar.HOUR_OF_DAY, date.getHours());
			calendar.set(Calendar.MINUTE, date.getMinutes());
			calendar.set(Calendar.SECOND, date.getSeconds());
			calendar.set(Calendar.MILLISECOND, 999);
			dateTo = calendar.getTime();
		} else {
			calendar.set(Calendar.HOUR_OF_DAY, 23);
			calendar.set(Calendar.MINUTE, 59);
			calendar.set(Calendar.SECOND, 59);
			calendar.set(Calendar.MILLISECOND, 999);
			dateTo = calendar.getTime();
			dateTo = addMinutesToJavaUtilDate(dateTo, Constant.IST_OFFSET_IN_MINUTES);
		}
		DateFormat df = new SimpleDateFormat(Constant.DATE_FORMAT_YYYY_MM_DD_T_HH_MM_SS_Z);
		return df.format(dateTo);
	}

	public static String getFuelToDate(Long toDate) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(new Date(toDate));
		Date dateTo = null;
		dateTo = calendar.getTime();
		dateTo = addMinutesToJavaUtilDate(dateTo, -Constant.IST_OFFSET_IN_MINUTES);
		DateFormat df = new SimpleDateFormat(Constant.DATE_FORMAT_YYYY_MM_DD_T_HH_MM_SS_Z);
		return df.format(dateTo);
	}

	public static Date getISTDateWithTime(Long toDate) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(new Date(toDate));
		Date date = calendar.getTime();
		date = addMinutesToJavaUtilDate(date, (Constant.IST_OFFSET_IN_MINUTES));
		SimpleDateFormat isoFormat = new SimpleDateFormat("E MMM dd HH:mm:ss Z yyyy");
		isoFormat.setTimeZone(TimeZone.getTimeZone("IST"));
		try {
			date = isoFormat.parse(date.toString());
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return date;
	}

	public static Date getISTDateWithTimeWhStart(Long toDate) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(new Date(toDate));
		Date date = calendar.getTime();
		date = setwhStartDate(date.getTime());
		date = addMinutesToJavaUtilDate(date, (Constant.IST_OFFSET_IN_MINUTES));
		SimpleDateFormat isoFormat = new SimpleDateFormat("E MMM dd HH:mm:ss Z yyyy");
		isoFormat.setTimeZone(TimeZone.getTimeZone("IST"));
		try {
			date = isoFormat.parse(date.toString());
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return date;
	}

	public static Date getISTDateWithTimeWhEnd(Long toDate) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(new Date(toDate));
		Date date = calendar.getTime();
		date = setwhEndDate(date.getTime());
		date = addMinutesToJavaUtilDate(date, (Constant.IST_OFFSET_IN_MINUTES));
		SimpleDateFormat isoFormat = new SimpleDateFormat("E MMM dd HH:mm:ss Z yyyy");
		isoFormat.setTimeZone(TimeZone.getTimeZone("IST"));
		try {
			date = isoFormat.parse(date.toString());
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return date;
	}

	public static Long convertMilliToMin(Long milli) {
		if (milli == null) {
			return 0l;
		}
		return TimeUnit.MILLISECONDS.toMinutes(milli);
	}

	public static float diffDateInMinutes(Date from, Date to) {
		Long dateDiff = Math.abs(from.getTime() - to.getTime());
		return (float) dateDiff / (60 * 1000);
	}

	public static Date addMinutesToJavaUtilDate(Date date, int minutes) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.MINUTE, minutes);
		date = calendar.getTime();
		return date;
	}

	public static Date addMinutesToJavaUtilDateWithISTZone(Date date, int minutes) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.MINUTE, minutes);
		date = calendar.getTime();
		SimpleDateFormat isoFormat = new SimpleDateFormat("E MMM dd HH:mm:ss Z yyyy");
		isoFormat.setTimeZone(TimeZone.getTimeZone("IST"));
		try {
			date = isoFormat.parse(date.toString());
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return date;
	}

	public static String getFuelToDatePlusMinutes(Long fromDate, Long minutesToAdd) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(new Date(fromDate));
		Date dateFrom = cal.getTime();
		dateFrom = addMinutesToJavaUtilDate(dateFrom, -Constant.IST_OFFSET_IN_MINUTES);
		dateFrom = addMinutesToJavaUtilDate(dateFrom, minutesToAdd.intValue());
		DateFormat df = new SimpleDateFormat(Constant.DATE_FORMAT_YYYY_MM_DD_T_HH_MM_SS_Z);
		return df.format(dateFrom);

	}

	public static String getFuelToDateMinusMinutes(Long toDate, Long minutesToMinus) {
		Date date = new Date();
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(new Date(toDate));
		Date dateTo = null;
		if (calendar.getTime().getDate() == date.getDate()) {
			calendar.set(Calendar.HOUR_OF_DAY, date.getHours());
			calendar.set(Calendar.MINUTE, date.getMinutes());
			calendar.set(Calendar.SECOND, date.getSeconds());
			calendar.set(Calendar.MILLISECOND, 999);
			dateTo = calendar.getTime();
		} else {
			dateTo = calendar.getTime();
			dateTo = addMinutesToJavaUtilDate(dateTo, -Constant.IST_OFFSET_IN_MINUTES);
			dateTo = addMinutesToJavaUtilDate(dateTo, -minutesToMinus.intValue());
		}
		DateFormat df = new SimpleDateFormat(Constant.DATE_FORMAT_YYYY_MM_DD_T_HH_MM_SS_Z);
		return df.format(dateTo);
	}

	public static Boolean checkDateTodayDate(Long toDate) {
		Date date = new Date();
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(new Date(toDate));
		if (calendar.getTime().getDate() == date.getDate()) {
			return true;
		} else {
			return false;
		}
	}

	public static Date dateFormatter(Long Date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(new Date(Date));
		Date date = cal.getTime();
		date = cal.getTime();
		SimpleDateFormat isoFormat = new SimpleDateFormat(Constant.DATE_FORMAT_YYYY_MM_DD);
		try {
			date = isoFormat.parse(date.toString());
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return date;
	}

	public static String dateFormatterToString(Long Date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(new Date(Date));
		Date date = cal.getTime();
		date = cal.getTime();
		SimpleDateFormat isoFormat = new SimpleDateFormat(Constant.DATE_FORMAT_YYYY_MM_DD);
		try {
			return isoFormat.format(date);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public static Date getDateByHourMin(Long toDate, Integer hour, Integer minutes) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(new Date(toDate));
		calendar.set(Calendar.HOUR_OF_DAY, hour);
		calendar.set(Calendar.MINUTE, minutes);
		calendar.set(Calendar.SECOND, 00);
		Date date = calendar.getTime();
		// dateTo = addMinutesToJavaUtilDate(dateTo, -(Constant.IST_OFFSET_IN_MINUTES));
		return date;
	}

	public static LocalDate convertToLocalDate(Date dateToConvert) {
		return dateToConvert.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
	}

	public static LocalDate firstDayOfPreviousMonth(LocalDate date) {
		return date.minusMonths(1).withDayOfMonth(1);
	}

	public static LocalDate lastDayOfCurrentMonth(LocalDate date) {
		return date.plusDays(date.lengthOfMonth() - 1);
	}

	public static LocalDate lastDayOfPreviousMonth(LocalDate date) {
		return date.withDayOfMonth(1).minusDays(1);
	}

	public static Date convertLocalDateToDate(LocalDate date) throws ParseException {
		return new SimpleDateFormat("yyyy-MM-dd").parse(date.toString());
	}

	public static Date getDateOfEarlyGivenDaysFromGivenDate(Date forDate, Integer daysBefore) {

		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DATE, -daysBefore);
		return cal.getTime();
	}

	public static Date getDateOfAfterGivenDaysFromGivenDate(Date forDate, Integer daysBefore) {

		Calendar cal = Calendar.getInstance();
		cal.setTime(forDate);
		;
		cal.add(Calendar.DATE, daysBefore);
		return cal.getTime();
	}

	public static Date minusMinutesToJavaUtilDate(Date date, int minutes) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.MINUTE, minutes);
		date = calendar.getTime();
		return date;
	}

	public static String getFromDateWithFromatV1(Long fromDate) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(new Date(fromDate));
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 1);
		cal.set(Calendar.MILLISECOND, 0);
		Date dateFrom = cal.getTime();
		DateFormat df = new SimpleDateFormat(Constant.DATE_FORMAT_YYYY_MM_DD);
		return df.format(dateFrom);
	}

	public static Date getCalculatedDate(String tripDate, String hourMin) {
		Date date = null;
		try {
			date = new SimpleDateFormat("dd-MM-yyyy").parse(tripDate);
			Calendar cal = Calendar.getInstance();
			cal.setTime(date);
			String[] split = hourMin.split(":");
			Float hrs = Float.parseFloat(split[0]);
			Float min = Float.parseFloat(split[1]);
			cal.set(Calendar.HOUR_OF_DAY, hrs.intValue());
			cal.set(Calendar.MINUTE, min.intValue());
			date = cal.getTime();
			date = DateUtil.addMinutesToJavaUtilDate(date, -Constant.IST_OFFSET_IN_MINUTES);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return date;
	}

	public static Float getDurationInHrsAfterSplit(String time) {
		try {
			if (time == null || time.isEmpty())
				return 0f;

			String[] split = time.split(":");
			float hrs = Float.parseFloat(split[0]);
			float min = Float.parseFloat(split[1]);
			float totalHrs = hrs + (min / 60);
			return totalHrs;
		} catch (Exception ex) {
			ex.printStackTrace();
			return 0f;
		}
	}

	public static String getFormateDate(String date) throws ParseException {
		DateFormat inputFormatter = new SimpleDateFormat("dd-MM-yyyy");
		Date da = (Date) inputFormatter.parse(date);
		DateFormat outputFormatter = new SimpleDateFormat("yyyy-MM-dd");
		String strDateTime = outputFormatter.format(da);
		return strDateTime;
	}

	public static String getFuelToDateV2(Long toDate) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(new Date(toDate));
		Date dateTo = null;
		dateTo = calendar.getTime();
		dateTo = addMinutesToJavaUtilDate(dateTo, 1440);
		dateTo = addMinutesToJavaUtilDate(dateTo, -Constant.IST_OFFSET_IN_MINUTES);
		DateFormat df = new SimpleDateFormat(Constant.DATE_FORMAT_YYYY_MM_DD_T_HH_MM_SS_Z);
		return df.format(dateTo);
	}

	public static Date getFuelFromDateV2(Long fromDate) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(new Date(fromDate));
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MILLISECOND, 0);
		Date dateFrom = cal.getTime();
		dateFrom = addMinutesToJavaUtilDate(dateFrom, -Constant.IST_OFFSET_IN_MINUTES);
		DateFormat df = new SimpleDateFormat(Constant.DATE_FORMAT_YYYY_MM_DD_T_HH_MM_SS_Z);
		return dateFrom;
	}

	public static Boolean getDateBefore(String date1, String date2) {
		try {
			Date date = new SimpleDateFormat("yyyy-MM-dd").parse(date1);
			Date reqDate = new SimpleDateFormat("yyyy-MM-dd").parse(date2);
			boolean result = date.before(reqDate);
			return result;
		} catch (Exception e) {
			//
			return null;
		}
	}

	public static List<Date> getListOfDaysBetweenTwoDates(Date startDate, Date endDate) {
		List<Date> result = new ArrayList<Date>();
		Calendar start = Calendar.getInstance();
		start.setTime(startDate);
		Calendar end = Calendar.getInstance();
		end.setTime(endDate);
		end.add(Calendar.DAY_OF_YEAR, 1); // Add 1 day to endDate to make sure endDate is included into the final list
		while (start.before(end)) {
			result.add(start.getTime());
			System.out.println(start.getTime());
			start.add(Calendar.DAY_OF_YEAR, 1);
		}
		return result;
	}

	public static Date getDateFromUtcToISTDateFromat(Date fromDate) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(fromDate);
		cal.add(Calendar.HOUR, +5);
		cal.add(Calendar.MINUTE, +30);
		Date dateFrom = cal.getTime();
		return dateFrom;
	}

	public static Date getFromDateWithOutAddTimeInUTCDateFromat(Date fromDate) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(fromDate);
		cal.add(Calendar.HOUR, -5);
		cal.add(Calendar.MINUTE, -30);
		Date dateFrom = cal.getTime();
		return dateFrom;
	}

	public static String getFromDateWithOutAddTimeInUTCFromat(Long fromDate) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(new Date(fromDate));
		cal.add(Calendar.HOUR, -5);
		cal.add(Calendar.MINUTE, -30);
		Date dateFrom = cal.getTime();
		DateFormat df = new SimpleDateFormat(Constant.DATE_FORMAT_YYYY_MM_DD_T_HH_MM_SS_Z);
		return df.format(dateFrom);
	}

	public static long getMailDate() {
		Calendar cal = Calendar.getInstance();
		cal.setTime(new Date());
		cal.set(Calendar.DAY_OF_MONTH, 25);
		cal.set(Calendar.HOUR, 18);
		cal.set(Calendar.MINUTE, 15);
		cal.set(Calendar.MINUTE, 00);
		Date dateFrom = cal.getTime();
		return dateFrom.getTime();
	}

	public static Date convertDate(Date date, String name) {
//		TimeZoneMaster find = timeZoneMasterRepository.findByName(name);
//		Date respDate = addMinutesToJavaUtilDate(date, 0);
		return null;
	}

}
