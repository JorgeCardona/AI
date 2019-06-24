import math

class Evaluate():

    def saludo (self, mensaje):
        return mensaje

    def add_distance(self,origin):
        
        self.df_db_copy["Distance"] = '\n' 
        #self.df_db_copy.apply(lambda row: self.calculate_distance(origin, [row["Latitude"], row["Longitude"]]), axis=1)
        self.df_db_copy.apply(lambda row: self.calculate_distance(origin, origin), axis=1)

    #recibe en origin un vector de dos posiciones y en destination tambien
    def calculate_distance(self, origin, destination):
        lat1, lon1 = origin
        lat2, lon2 = destination
        
        radius = 6371  # km
        dlat = math.radians(lat2 - lat1)
        dlon = math.radians(lon2 - lon1)
        a = (math.sin(dlat / 2) * math.sin(dlat / 2) +
             math.cos(math.radians(lat1)) * math.cos(math.radians(lat2)) *
             math.sin(dlon / 2) * math.sin(dlon / 2))
        c = 2 * math.atan2(math.sqrt(a), math.sqrt(1 - a))
        d = radius * c
        return d

    def drop_columns(self):
        self.df_db_copy = self.df_db_copy.drop(labels=self.dropcolumns, axis=1)

    def get_coordinates(self, village, zone):

        ep = "https://maps.googleapis.com/maps/api/geocode/json"
        #key = "AIzaSyDhud0erwYliXv7ER_YdVtMTl2ySL759qQ"
        key = "AIzaSyAS3MvSUnKolwWwmxJrbtFA1-ANGNGO1SQ"
        p = {'address': village+","+zone, 'key': key}
        try:
            r = requests.get(url=ep, params=p)
            data = r.json()
            print(data)
            lat = data['results'][0]['geometry']['location']['lat']
            lng = data['results'][0]['geometry']['location']['lng']
            return lat, lng
            #self.origin = [lat,lng]
        except:
            return 0, 0

    def gower_similarity_coeff(self):
        rk = 1
        self.df_db["similarity"] = self.df_db_copy.apply(lambda row: sum((1-(abs(row - self.house.iloc[0]))/(row.max()-row.min())) * self.valores_peso_inicial.iloc[0])/sum(self.valores_peso_inicial.iloc[0]), axis=1)

    def houses2dict(self):
        self.df_db = self.df_db.sort_values(by='similarity', ascending=False)
        return self.df_db.iloc[0:5].to_dict(orient='records')

    def load_weights(self):
        factor = 0.2/self.valores_peso_inicial[self.caracteristica_basica].sum(axis=1)
        self.valores_peso_inicial[self.caracteristica_basica] = self.valores_peso_inicial[self.caracteristica_basica]*factor.values[0]

    def normalize(self):
        #self.df_db_copy.loc[self.df_db_copy["Value"] < self.house.iloc[0]["Value"],"Value"]= self.house.iloc[0]["Value"]

        self.df_db_temp = pd.concat([self.house, self.df_db_copy])
                
        max_per_column = self.df_db_temp.max()
        min_per_column = self.df_db_temp.min()

        divisor = self.df_db_temp.max()-self.df_db_temp.min()
        divisor.loc[divisor==0] = self.df_db_temp.loc[:, divisor==0].max()
        #print(divisor)

        self.df_db_copy = (self.df_db_copy - min_per_column) / (divisor)

        self.house = (self.house - min_per_column) / (divisor)

    def normalize_gauss(self):

        #colum_num = ['Value', 'Bedrooms', 'Distance']
        colum_num = ['PRECIO', 'HABITACIONES', 'DISTANCIA']
        mean_per_column = self.df_db_copy[colum_num].mean()
        std_per_column = self.df_db_copy[colum_num].std()

        self.df_db_copy[colum_num] = (self.df_db_copy[colum_num] - mean_per_column) / std_per_column
        self.house[colum_num] = (self.house[colum_num] - mean_per_column) / std_per_column

    def pieChart(self):
            import matplotlib.pyplot as plt
            #sizes = [215, 130, 245, 210]
            colors = ['gold', 'yellowgreen', 'lightcoral', 'lightskyblue']
            explode = [0.1]*(len(self.valores_peso_inicial[self.valores_peso_inicial!=0].iloc[0].dropna()))
            self.valores_peso_inicial[self.valores_peso_inicial!=0].iloc[0].dropna().plot.pie(explode=explode,colors=colors,autopct='%1.1f%%', startangle=240)
            plt.axis('equal')
            plt.show()  

    def process_data(self,origin):
        self.load_weights()  # weights
        self.df_db_copy = self.df_db
        self.add_distance(origin)
        self.drop_columns()
        self.normalize()
        self.gower_similarity_coeff()
        self.create_excel()
        self.df_result += self.houses2dict()

    def result(self):
        return self.df_result