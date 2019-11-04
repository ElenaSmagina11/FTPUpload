# FTPUpload
toDo

Erstellen sie ein Programm (jar), das Dareien per FTP hochlädt.
Die Parametrisierung erfolgt über die config.json im gleichen Verzeichnis, wie die jar-Datei.
Die hochzuladenen Dateien liegen im Ordner source.
Nach dem Upload sollen die Dateien in jeweils in das Backupverzeichnis in einen
 Unterordner/<Kundenummer> verschoben werden.<br>
 Schlägt der Upload fehl, soll dieser bis zu dreimal wiederholen werden.
 Unbekannte Dateien mit der zu suchenden Erweiterung werden wie fehlgeschlagene Uploads behandelt.
 Nach dem dritten Fehlschlag (oder unbekannte Kunde) soll die Dateien in den failed-Ordner
 verschoben werden.<br>
 Schreiben Sie eine Logzeile pro Upload mit Kundenummer mit mindestens (customer), 
 Dateiname ohne Pfad (file), Ziel (name) und Zeitstempel und Erfolgameldung (msg).<br>
 Die Kundenummer (String) ergibt sich aus den ersten eichen des Dateinamens vor dem Unterstrich.
 Beachten Sie: bei zwei der Kunden ist ein Unterverzeichnis auf dem Remote-Server
 anzulegen und zu befüllen.<br>
 
 Erstellen Sie Hauptklasse als de.firma.ftpUpload.java.
 Rufen Sie mit der main die Funkzion boolean process(String configJsonFole) auf.
 Geben Sie false bei process zurück, wenn mindestens ein Fehler auftritt.
 Das Programm soll auch bei Fehlern versuchen alle Dateien hochzuladen.
 <br>
 Liste den Dateien:
 
 12345_Kunde1:_0001.zip<br>
 67890_Kunde2_0001.zip<br>
 24680_Kunde3_0001.zip<br>
 12345_Kunde1_0002.zip<br>
 67890_Kunde2_0002.zip<br>
 24680_Kunde3_0004.zip<br>
 13569_Kunde3_0004.zip
