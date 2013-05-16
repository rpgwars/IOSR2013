package pl.agh.edu.carecenter.server.dao;

import pl.agh.edu.carecenter.server.domain.Alarm;
import pl.agh.edu.carecenter.server.domain.Patient;

public interface AlarmDAO extends GenericDAO<Alarm>{

	void saveAlarm(Alarm alarm, String username);
	Patient getPatientByAlarmId(Integer alarmId);
}
