
def createGiraphInput(file_name, ext="csv"):
	loc_file = open(file_name + "." + ext, "r")
        out_file = open(file_name + "Giraph.txt", "w")
	loc_file.readline()
	loc_data = loc_file.readlines()

        i = 0;
	for line in loc_data:
		row = line.rstrip().split(",")
		line_out = "[" + str(i) + "," + row[1] + "," + row[2] + "]"
		i += 1
		out_file.write(line_out + "\n")
	loc_file.close()
	out_file.close()





createGiraphInput("spain_locs")
