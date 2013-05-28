f = open("Ourlogs.txt", "r")
f2 = open("outlogs2.txt", "w")
for line in f:
	line = line.replace('\n', '\r\n')
	f2.write(line)
f.close()
f2.close()
