# Dünn besetztes Array (Zusatzaufgabe)

Ein Array ist eine Datenstruktur fester Größe mit durchnummerierten Einträgen (bei 0 beginnend), die einen schnellen Zugriff auf jeden beliebigen Einträg ermöglicht.
Wenn ein Großteil der Array-Einträge einen einzelnen, festen Standardwert `D` annimmt, spricht man von einem _dünn besetzten Array_ (englisch _sparse array_).
Dünn besetzte Arrays lassen sich effizienter speichern, indem man sich nur die Einträge merkt, die nicht den Wert `D` annehmen (z.B. mit einer HashMap).

Beispiele für dünn besetzte Arrays:

`0 0 5 0 0 0 0 0 3` (Größe 9, `D` = `0`)

`x # # # # y # # # # # z # # #` (Größe 15, `D` = `#`)

Lösen Sie jetzt die folgenden Aufgaben:

1. Forken und klonen Sie dieses Repository.
2. Betrachten Sie die Codevorlage in `src/main/scala/SparseArray.scala`. Implementieren Sie die fehlenden Methoden gemäß der Scaladoc-Kommentare. Denken Sie daran, dass der Standardwert `D` **nie** (**nie!**) in der Map gespeichert werden soll - wir kontrollieren das!
3. Laden Sie Ihre Lösung hoch, indem Sie den veränderten Code in Ihr geforktes Repository pushen.
4. Fügen Sie die Nutzer `@juhenneb` und `@fschuhkn` Ihrem GitLab-Repository hinzu (mit der Rolle `Reporter`). Wenn Sie das nicht tun, kann Ihre Abgabe nicht bewertet werden, und wir können keine Zusatzpunkte vergeben.
