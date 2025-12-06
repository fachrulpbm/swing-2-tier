# swing-2-tier
Penerapan 2-Tier Architecture Pattern (Client Tier, Data Tier) dan Layered Architecture Pattern (Presentation Layer, Business Logic Layer, Data Access Layer, Data Source Layer) pada studi kasus Aplikasi Desktop Java Swing menggunakan pustaka MigLayout dan FlatLaf, yang terhubung ke DBMS MySql, serta mendukung eksekusi background menggunakan SwingWorker sehingga UI tetap interaktif (tidak mengalami UI Freezing)

<h3>2-Tier Architecture Pattern</h3>
<ol>
  <li><b>Client-Tier (Java Swing)</b> https://github.com/fachrulpbm/swing-2-tier</li>
    <ul>
      <li><b>Presentation Layer</b> (MVC Pattern)</li>
        <ul>
          <li>model</li>
          <li>view</li>
          <li>controller</li>
          <li>service</li>
          <li>dao</li>
          <li>worker (background thread)</li>
          <li>config</li>
        </ul>
    </ul>
  <li><b>Data-Tier (DBMS MySQL)</b></li>
    <ul>
      <li><b>Data Source Layer</b></li>
        <ul>
          <li>Database Engine</li>
        </ul>
    </ul>  
</ol>

<br>

<img width="986" height="634" alt="image" src="https://github.com/user-attachments/assets/181f2064-1ca3-4a2c-9e1d-12a79ab0479e" />
<br><br>
<img width="986" height="634" alt="image" src="https://github.com/user-attachments/assets/12724300-eb6b-42e6-ab35-93d91e331d63" />
<br><br>
<img width="987" height="635" alt="image" src="https://github.com/user-attachments/assets/5eac0cfa-e784-4fb0-ac87-731da1f2d300" />
<br><br>
<img width="986" height="634" alt="image" src="https://github.com/user-attachments/assets/6f29520c-9c6f-45f1-b9f0-e327b6bccc87" />
<br><br>
<img width="986" height="636" alt="image" src="https://github.com/user-attachments/assets/c1b8fdb2-c352-4849-ae62-12e8415abe4f" />
<br><br>
<img width="984" height="633" alt="image" src="https://github.com/user-attachments/assets/01f2c36a-40b7-4e1b-8801-9b161f8f59c8" />
