package application;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;

public class SecController {
	
	int nRes;
	int nProc;
	int[] availRes= new int[5];
	
	@FXML
    private Label nr;

    @FXML
    private Label np;
	
	
	public void initData(String x, String y)
	{
		nRes=Integer.parseInt(x);
		nProc=Integer.parseInt(y);
		nr.setText(x);
		np.setText(y);
	}
	@FXML
    private TextField r1;

    @FXML
    private TextField r2;

    @FXML
    private TextField r3;

    @FXML
    private TextField r4;

    @FXML
    private TextField r5;

    @FXML
    private TextField p11;

    @FXML
    private TextField p12;

    @FXML
    private TextField p13;

    @FXML
    private TextField p14;

    @FXML
    private TextField p15;

    @FXML
    private TextField p21;

    @FXML
    private TextField p22;

    @FXML
    private TextField p23;

    @FXML
    private TextField p24;

    @FXML
    private TextField p25;

    @FXML
    private TextField p31;

    @FXML
    private TextField p32;

    @FXML
    private TextField p33;

    @FXML
    private TextField p34;

    @FXML
    private TextField p35;

    @FXML
    private TextField p41;

    @FXML
    private TextField p42;

    @FXML
    private TextField p43;

    @FXML
    private TextField p44;

    @FXML
    private TextField p45;

    @FXML
    private TextField p51;

    @FXML
    private TextField p52;

    @FXML
    private TextField p53;

    @FXML
    private TextField p54;

    @FXML
    private TextField p55;
    
    @FXML
    private TextField pm11;

    @FXML
    private TextField pm12;

    @FXML
    private TextField pm13;

    @FXML
    private TextField pm14;

    @FXML
    private TextField pm15;

    @FXML
    private TextField pm21;

    @FXML
    private TextField pm22;

    @FXML
    private TextField pm23;

    @FXML
    private TextField pm24;

    @FXML
    private TextField pm25;

    @FXML
    private TextField pm31;

    @FXML
    private TextField pm32;

    @FXML
    private TextField pm33;

    @FXML
    private TextField pm34;

    @FXML
    private TextField pm35;

    @FXML
    private TextField pm41;

    @FXML
    private TextField pm42;

    @FXML
    private TextField pm43;

    @FXML
    private TextField pm44;

    @FXML
    private TextField pm45;

    @FXML
    private TextField pm51;

    @FXML
    private TextField pm52;

    @FXML
    private TextField pm53;

    @FXML
    private TextField pm54;

    @FXML
    private TextField pm55;

    @FXML
    private Button submit2;
    
    @FXML
    private GridPane needM;
    
    @FXML
    private Label state;

    @FXML
    private Label seq;
    
    
    
    int[] skipProc=new int[5];
    
    @FXML
    void submit2action(ActionEvent event) {
    	createneed();
    	for(int i=0;i<nProc;i++)
    	{
    		for(int j=0;j<nRes;j++)
    		{
    			Label label = new Label(Integer.toString(needMatrix[i][j]));
    			needM.add(label, j, i);
    		}
    	}
    	/*
    	 * Need matrix created and displayed done. From here below onwards write the algo to find the process and display them!
    	 */
    	int flag=0,ultFlag=0;
    	boolean skip=false;
    	int x=0,z=0;
    	int[] completedProcess=new int[6];
    	for(int i=0;i<nProc;i++)
    	{
    		for(int j=0;j<nRes;j++)
    		{
    			if(needMatrix[i][j]>availRes[j])
    			{
    				flag=1;
    				skip=true;
    			}
    		}
    		if(flag==0) {
    			procContinue(i);
    			completedProcess[z]=i;
    			z++;
    			flag=0;
    		}
    		else if(flag==1) {
    			skipProc[x]=i;
				x++;
				flag=0;
    		}
    	}
    	if(skip==true) {
    		int y=0;
        	
        	for(int i=0;i<x;i++)
        	{
        		int x1=skipProc[y];
        		for(int j=0;j<nRes;j++)
        		{
        			if(needMatrix[x1][j]>availRes[j])
        			{
        				ultFlag=1;
        			}
        		}
        		if(ultFlag==0) {
        			procContinue(x1);
        			completedProcess[z]=x1;
        			z++;
        			y++;
        		}	
        	}
        	if(ultFlag==1) {
        		state.setTextFill(Color.color(1, 0, 0));
        		state.setText("System is in Unsafe State");
        	}
        	else {
        		state.setTextFill(Color.color(0, 0.8, 0.1));
        		state.setText("System is in Safe State");
        		String b=".";
    			for(int i=0;i<z;i++) {
    				String c=String.valueOf(completedProcess[i]+1);
    				b=b+"P"+c+"-> ";
    			}
    			seq.setText(b);
    		}
    	}

    	else {
    		state.setTextFill(Color.color(0, 0.8, 0.1));
    		state.setText("System is in Safe State");
    		String b=".";
			for(int i=0;i<z;i++) {
				String c=String.valueOf(completedProcess[i]+1);
				b=b+"P"+c+"-> ";
			}
			seq.setText(b);
		}
    	
    }
    
    void procContinue(int procno) {
    	int[] newAlloc= new int [5];
    	int[] availPrime= new int [5];
    	int[] availDoublePrime= new int [5];
    	int[] need=new int[5];
    	int[] current=new int[5];
    	for(int i=0;i<nRes;i++)
    	{
    		need[i]=needMatrix[procno][i];
    		current[i]=currentAlloc[procno][i];
    		newAlloc[i]=current[i] + need[i];
    		availPrime[i]=availRes[i]-need[i];
    		availDoublePrime[i]=newAlloc[i]+availPrime[i];
    		availRes[i]=availDoublePrime[i];
    	}
    	
    }
    
    int[][] needMatrix= new int[5][5];
    int[][] currentAlloc= new int[5][5];
    void createneed() {
    	if(nRes==1 && nProc==1) 
    	{
    		availRes[0]=Integer.parseInt(r1.getText());
    		needMatrix[0][0]=(Integer.parseInt(pm11.getText()))-(Integer.parseInt(p11.getText()));
    		currentAlloc[0][0]=Integer.parseInt(p11.getText());
    	} 
    	else if(nRes==1 && nProc==2) 
    	{
    		availRes[0]=Integer.parseInt(r1.getText());
    		needMatrix[0][0]=(Integer.parseInt(pm11.getText()))-(Integer.parseInt(p11.getText()));
    		needMatrix[1][0]=(Integer.parseInt(pm21.getText()))-(Integer.parseInt(p21.getText()));
    		currentAlloc[0][0]=Integer.parseInt(p11.getText());
    		currentAlloc[1][0]=Integer.parseInt(p21.getText());
    	}
    	else if(nRes==1 && nProc==3) 
    	{
    		availRes[0]=Integer.parseInt(r1.getText());
    		needMatrix[0][0]=(Integer.parseInt(pm11.getText()))-(Integer.parseInt(p11.getText()));
    		needMatrix[1][0]=(Integer.parseInt(pm21.getText()))-(Integer.parseInt(p21.getText()));
    		needMatrix[2][0]=(Integer.parseInt(pm31.getText()))-(Integer.parseInt(p31.getText()));
    		currentAlloc[0][0]=Integer.parseInt(p11.getText());
    		currentAlloc[1][0]=Integer.parseInt(p21.getText());
    		currentAlloc[2][0]=Integer.parseInt(p31.getText());
    	}
    	else if(nRes==1 && nProc==4) 
    	{
    		availRes[0]=Integer.parseInt(r1.getText());
    		needMatrix[0][0]=(Integer.parseInt(pm11.getText()))-(Integer.parseInt(p11.getText()));
    		needMatrix[1][0]=(Integer.parseInt(pm21.getText()))-(Integer.parseInt(p21.getText()));
    		needMatrix[2][0]=(Integer.parseInt(pm31.getText()))-(Integer.parseInt(p31.getText()));
    		needMatrix[3][0]=(Integer.parseInt(pm41.getText()))-(Integer.parseInt(p41.getText()));
    		currentAlloc[0][0]=Integer.parseInt(p11.getText());
    		currentAlloc[1][0]=Integer.parseInt(p21.getText());
    		currentAlloc[2][0]=Integer.parseInt(p31.getText());
    		currentAlloc[3][0]=Integer.parseInt(p41.getText());
    	}
    	else if(nRes==1 && nProc==5) 
    	{
    		availRes[0]=Integer.parseInt(r1.getText());
    		needMatrix[0][0]=(Integer.parseInt(pm11.getText()))-(Integer.parseInt(p11.getText()));
    		needMatrix[1][0]=(Integer.parseInt(pm21.getText()))-(Integer.parseInt(p21.getText()));
    		needMatrix[2][0]=(Integer.parseInt(pm31.getText()))-(Integer.parseInt(p31.getText()));
    		needMatrix[3][0]=(Integer.parseInt(pm41.getText()))-(Integer.parseInt(p41.getText()));
    		needMatrix[4][0]=(Integer.parseInt(pm51.getText()))-(Integer.parseInt(p51.getText()));
    		currentAlloc[0][0]=Integer.parseInt(p11.getText());
    		currentAlloc[1][0]=Integer.parseInt(p21.getText());
    		currentAlloc[2][0]=Integer.parseInt(p31.getText());
    		currentAlloc[3][0]=Integer.parseInt(p41.getText());
    		currentAlloc[4][0]=Integer.parseInt(p51.getText());
    	}
    	else if(nRes==2 && nProc==1)
    	{
    		availRes[0]=Integer.parseInt(r1.getText());
    		availRes[1]=Integer.parseInt(r2.getText());
    		needMatrix[0][0]=(Integer.parseInt(pm11.getText()))-(Integer.parseInt(p11.getText()));
        	needMatrix[0][1]=(Integer.parseInt(pm12.getText()))-(Integer.parseInt(p12.getText()));
        	currentAlloc[0][0]=Integer.parseInt(p11.getText());
        	currentAlloc[0][1]=Integer.parseInt(p12.getText());
        	
    	}
    	else if(nRes==2 && nProc==2)
    	{
    		availRes[0]=Integer.parseInt(r1.getText());
    		availRes[1]=Integer.parseInt(r2.getText());
    		needMatrix[0][0]=(Integer.parseInt(pm11.getText()))-(Integer.parseInt(p11.getText()));
        	needMatrix[0][1]=(Integer.parseInt(pm12.getText()))-(Integer.parseInt(p12.getText()));
        	needMatrix[1][0]=(Integer.parseInt(pm21.getText()))-(Integer.parseInt(p21.getText()));
        	needMatrix[1][1]=(Integer.parseInt(pm22.getText()))-(Integer.parseInt(p22.getText()));
        	currentAlloc[0][0]=Integer.parseInt(p11.getText());
        	currentAlloc[0][1]=Integer.parseInt(p12.getText());
    		currentAlloc[1][0]=Integer.parseInt(p21.getText());
    		currentAlloc[1][1]=Integer.parseInt(p22.getText());
    	}
    	else if(nRes==2 && nProc==3)
    	{
    		availRes[0]=Integer.parseInt(r1.getText());
    		availRes[1]=Integer.parseInt(r2.getText());
    		needMatrix[0][0]=(Integer.parseInt(pm11.getText()))-(Integer.parseInt(p11.getText()));
        	needMatrix[0][1]=(Integer.parseInt(pm12.getText()))-(Integer.parseInt(p12.getText()));
        	needMatrix[1][0]=(Integer.parseInt(pm21.getText()))-(Integer.parseInt(p21.getText()));
        	needMatrix[1][1]=(Integer.parseInt(pm22.getText()))-(Integer.parseInt(p22.getText()));
        	needMatrix[2][0]=(Integer.parseInt(pm31.getText()))-(Integer.parseInt(p31.getText()));
        	needMatrix[2][1]=(Integer.parseInt(pm32.getText()))-(Integer.parseInt(p32.getText()));
        	currentAlloc[0][0]=Integer.parseInt(p11.getText());
        	currentAlloc[0][1]=Integer.parseInt(p12.getText());
    		currentAlloc[1][0]=Integer.parseInt(p21.getText());
    		currentAlloc[1][1]=Integer.parseInt(p22.getText());
    		currentAlloc[2][0]=Integer.parseInt(p31.getText());
    		currentAlloc[2][1]=Integer.parseInt(p32.getText());
        	
    	}
    	else if(nRes==2 && nProc==4)
    	{
    		availRes[0]=Integer.parseInt(r1.getText());
    		availRes[1]=Integer.parseInt(r2.getText());
    		needMatrix[0][0]=(Integer.parseInt(pm11.getText()))-(Integer.parseInt(p11.getText()));
        	needMatrix[0][1]=(Integer.parseInt(pm12.getText()))-(Integer.parseInt(p12.getText()));
        	needMatrix[1][0]=(Integer.parseInt(pm21.getText()))-(Integer.parseInt(p21.getText()));
        	needMatrix[1][1]=(Integer.parseInt(pm22.getText()))-(Integer.parseInt(p22.getText()));
        	needMatrix[2][0]=(Integer.parseInt(pm31.getText()))-(Integer.parseInt(p31.getText()));
        	needMatrix[2][1]=(Integer.parseInt(pm32.getText()))-(Integer.parseInt(p32.getText()));
        	needMatrix[3][0]=(Integer.parseInt(pm41.getText()))-(Integer.parseInt(p41.getText()));
        	needMatrix[3][1]=(Integer.parseInt(pm42.getText()))-(Integer.parseInt(p42.getText()));
        	currentAlloc[0][0]=Integer.parseInt(p11.getText());
        	currentAlloc[0][1]=Integer.parseInt(p12.getText());
    		currentAlloc[1][0]=Integer.parseInt(p21.getText());
    		currentAlloc[1][1]=Integer.parseInt(p22.getText());
    		currentAlloc[2][0]=Integer.parseInt(p31.getText());
    		currentAlloc[2][1]=Integer.parseInt(p32.getText());
    		currentAlloc[3][0]=Integer.parseInt(p41.getText());
    		currentAlloc[3][1]=Integer.parseInt(p42.getText());
    	}
    	else if(nRes==2 && nProc==5)
    	{
    		availRes[0]=Integer.parseInt(r1.getText());
    		availRes[1]=Integer.parseInt(r2.getText());
    		needMatrix[0][0]=(Integer.parseInt(pm11.getText()))-(Integer.parseInt(p11.getText()));
        	needMatrix[0][1]=(Integer.parseInt(pm12.getText()))-(Integer.parseInt(p12.getText()));
        	needMatrix[1][0]=(Integer.parseInt(pm21.getText()))-(Integer.parseInt(p21.getText()));
        	needMatrix[1][1]=(Integer.parseInt(pm22.getText()))-(Integer.parseInt(p22.getText()));
        	needMatrix[2][0]=(Integer.parseInt(pm31.getText()))-(Integer.parseInt(p31.getText()));
        	needMatrix[2][1]=(Integer.parseInt(pm32.getText()))-(Integer.parseInt(p32.getText()));
        	needMatrix[3][0]=(Integer.parseInt(pm41.getText()))-(Integer.parseInt(p41.getText()));
        	needMatrix[3][1]=(Integer.parseInt(pm42.getText()))-(Integer.parseInt(p42.getText()));
        	needMatrix[4][0]=(Integer.parseInt(pm51.getText()))-(Integer.parseInt(p51.getText()));
        	needMatrix[4][1]=(Integer.parseInt(pm52.getText()))-(Integer.parseInt(p52.getText()));
        	currentAlloc[0][0]=Integer.parseInt(p11.getText());
        	currentAlloc[0][1]=Integer.parseInt(p12.getText());
    		currentAlloc[1][0]=Integer.parseInt(p21.getText());
    		currentAlloc[1][1]=Integer.parseInt(p22.getText());
    		currentAlloc[2][0]=Integer.parseInt(p31.getText());
    		currentAlloc[2][1]=Integer.parseInt(p32.getText());
    		currentAlloc[3][0]=Integer.parseInt(p41.getText());
    		currentAlloc[3][1]=Integer.parseInt(p42.getText());
    		currentAlloc[4][0]=Integer.parseInt(p51.getText());
    		currentAlloc[4][1]=Integer.parseInt(p52.getText());
        	
    	}
    	else if (nRes==3 && nProc==1) 
    	{
    		availRes[0]=Integer.parseInt(r1.getText());
    		availRes[1]=Integer.parseInt(r2.getText());
    		availRes[2]=Integer.parseInt(r3.getText());
    		needMatrix[0][0]=(Integer.parseInt(pm11.getText()))-(Integer.parseInt(p11.getText()));
        	needMatrix[0][1]=(Integer.parseInt(pm12.getText()))-(Integer.parseInt(p12.getText()));
        	needMatrix[0][2]=(Integer.parseInt(pm13.getText()))-(Integer.parseInt(p13.getText()));
        	currentAlloc[0][0]=Integer.parseInt(p11.getText());
        	currentAlloc[0][1]=Integer.parseInt(p12.getText());
        	currentAlloc[0][2]=Integer.parseInt(p13.getText());
		}
    	else if (nRes==3 && nProc==2) 
    	{
    		availRes[0]=Integer.parseInt(r1.getText());
    		availRes[1]=Integer.parseInt(r2.getText());
    		availRes[2]=Integer.parseInt(r3.getText());
    		needMatrix[0][0]=(Integer.parseInt(pm11.getText()))-(Integer.parseInt(p11.getText()));
        	needMatrix[0][1]=(Integer.parseInt(pm12.getText()))-(Integer.parseInt(p12.getText()));
        	needMatrix[0][2]=(Integer.parseInt(pm13.getText()))-(Integer.parseInt(p13.getText()));
        	needMatrix[1][0]=(Integer.parseInt(pm21.getText()))-(Integer.parseInt(p21.getText()));
        	needMatrix[1][1]=(Integer.parseInt(pm22.getText()))-(Integer.parseInt(p22.getText()));
        	needMatrix[1][2]=(Integer.parseInt(pm23.getText()))-(Integer.parseInt(p23.getText()));
        	currentAlloc[0][0]=Integer.parseInt(p11.getText());
        	currentAlloc[0][1]=Integer.parseInt(p12.getText());
        	currentAlloc[0][2]=Integer.parseInt(p13.getText());
        	currentAlloc[1][0]=Integer.parseInt(p21.getText());
        	currentAlloc[1][1]=Integer.parseInt(p22.getText());
        	currentAlloc[1][2]=Integer.parseInt(p23.getText());
		}
    	else if (nRes==3 && nProc==3) 
    	{
    		availRes[0]=Integer.parseInt(r1.getText());
    		availRes[1]=Integer.parseInt(r2.getText());
    		availRes[2]=Integer.parseInt(r3.getText());
    		needMatrix[0][0]=(Integer.parseInt(pm11.getText()))-(Integer.parseInt(p11.getText()));
        	needMatrix[0][1]=(Integer.parseInt(pm12.getText()))-(Integer.parseInt(p12.getText()));
        	needMatrix[0][2]=(Integer.parseInt(pm13.getText()))-(Integer.parseInt(p13.getText()));
        	needMatrix[1][0]=(Integer.parseInt(pm21.getText()))-(Integer.parseInt(p21.getText()));
        	needMatrix[1][1]=(Integer.parseInt(pm22.getText()))-(Integer.parseInt(p22.getText()));
        	needMatrix[1][2]=(Integer.parseInt(pm23.getText()))-(Integer.parseInt(p23.getText()));
        	needMatrix[2][0]=(Integer.parseInt(pm31.getText()))-(Integer.parseInt(p31.getText()));
        	needMatrix[2][1]=(Integer.parseInt(pm32.getText()))-(Integer.parseInt(p32.getText()));
        	needMatrix[2][2]=(Integer.parseInt(pm33.getText()))-(Integer.parseInt(p33.getText()));
        	currentAlloc[0][0]=Integer.parseInt(p11.getText());
        	currentAlloc[0][1]=Integer.parseInt(p12.getText());
        	currentAlloc[0][2]=Integer.parseInt(p13.getText());
        	currentAlloc[1][0]=Integer.parseInt(p21.getText());
        	currentAlloc[1][1]=Integer.parseInt(p22.getText());
        	currentAlloc[1][2]=Integer.parseInt(p23.getText());
        	currentAlloc[2][0]=Integer.parseInt(p31.getText());
        	currentAlloc[2][1]=Integer.parseInt(p32.getText());
        	currentAlloc[2][2]=Integer.parseInt(p33.getText());
		}
    	else if (nRes==3 && nProc==4) 
    	{
    		availRes[0]=Integer.parseInt(r1.getText());
    		availRes[1]=Integer.parseInt(r2.getText());
    		availRes[2]=Integer.parseInt(r3.getText());
    		needMatrix[0][0]=(Integer.parseInt(pm11.getText()))-(Integer.parseInt(p11.getText()));
        	needMatrix[0][1]=(Integer.parseInt(pm12.getText()))-(Integer.parseInt(p12.getText()));
        	needMatrix[0][2]=(Integer.parseInt(pm13.getText()))-(Integer.parseInt(p13.getText()));
        	needMatrix[1][0]=(Integer.parseInt(pm21.getText()))-(Integer.parseInt(p21.getText()));
        	needMatrix[1][1]=(Integer.parseInt(pm22.getText()))-(Integer.parseInt(p22.getText()));
        	needMatrix[1][2]=(Integer.parseInt(pm23.getText()))-(Integer.parseInt(p23.getText()));
        	needMatrix[2][0]=(Integer.parseInt(pm31.getText()))-(Integer.parseInt(p31.getText()));
        	needMatrix[2][1]=(Integer.parseInt(pm32.getText()))-(Integer.parseInt(p32.getText()));
        	needMatrix[2][2]=(Integer.parseInt(pm33.getText()))-(Integer.parseInt(p33.getText()));
        	needMatrix[3][0]=(Integer.parseInt(pm41.getText()))-(Integer.parseInt(p41.getText()));
        	needMatrix[3][1]=(Integer.parseInt(pm42.getText()))-(Integer.parseInt(p42.getText()));
        	needMatrix[3][2]=(Integer.parseInt(pm43.getText()))-(Integer.parseInt(p43.getText()));
        	currentAlloc[0][0]=Integer.parseInt(p11.getText());
        	currentAlloc[0][1]=Integer.parseInt(p12.getText());
        	currentAlloc[0][2]=Integer.parseInt(p13.getText());
        	currentAlloc[1][0]=Integer.parseInt(p21.getText());
        	currentAlloc[1][1]=Integer.parseInt(p22.getText());
        	currentAlloc[1][2]=Integer.parseInt(p23.getText());
        	currentAlloc[2][0]=Integer.parseInt(p31.getText());
        	currentAlloc[2][1]=Integer.parseInt(p32.getText());
        	currentAlloc[2][2]=Integer.parseInt(p33.getText());
        	currentAlloc[3][0]=Integer.parseInt(p41.getText());
        	currentAlloc[3][1]=Integer.parseInt(p42.getText());
        	currentAlloc[3][2]=Integer.parseInt(p43.getText());
		}
    	else if (nRes==3 && nProc==5) 
    	{
    		availRes[0]=Integer.parseInt(r1.getText());
    		availRes[1]=Integer.parseInt(r2.getText());
    		availRes[2]=Integer.parseInt(r3.getText());
    		needMatrix[0][0]=(Integer.parseInt(pm11.getText()))-(Integer.parseInt(p11.getText()));
        	needMatrix[0][1]=(Integer.parseInt(pm12.getText()))-(Integer.parseInt(p12.getText()));
        	needMatrix[0][2]=(Integer.parseInt(pm13.getText()))-(Integer.parseInt(p13.getText()));
        	needMatrix[1][0]=(Integer.parseInt(pm21.getText()))-(Integer.parseInt(p21.getText()));
        	needMatrix[1][1]=(Integer.parseInt(pm22.getText()))-(Integer.parseInt(p22.getText()));
        	needMatrix[1][2]=(Integer.parseInt(pm23.getText()))-(Integer.parseInt(p23.getText()));
        	needMatrix[2][0]=(Integer.parseInt(pm31.getText()))-(Integer.parseInt(p31.getText()));
        	needMatrix[2][1]=(Integer.parseInt(pm32.getText()))-(Integer.parseInt(p32.getText()));
        	needMatrix[2][2]=(Integer.parseInt(pm33.getText()))-(Integer.parseInt(p33.getText()));
        	needMatrix[3][0]=(Integer.parseInt(pm41.getText()))-(Integer.parseInt(p41.getText()));
        	needMatrix[3][1]=(Integer.parseInt(pm42.getText()))-(Integer.parseInt(p42.getText()));
        	needMatrix[3][2]=(Integer.parseInt(pm43.getText()))-(Integer.parseInt(p43.getText()));
        	needMatrix[4][0]=(Integer.parseInt(pm51.getText()))-(Integer.parseInt(p51.getText()));
        	needMatrix[4][1]=(Integer.parseInt(pm52.getText()))-(Integer.parseInt(p52.getText()));
        	needMatrix[4][2]=(Integer.parseInt(pm53.getText()))-(Integer.parseInt(p53.getText()));
        	currentAlloc[0][0]=Integer.parseInt(p11.getText());
        	currentAlloc[0][1]=Integer.parseInt(p12.getText());
        	currentAlloc[0][2]=Integer.parseInt(p13.getText());
        	currentAlloc[1][0]=Integer.parseInt(p21.getText());
        	currentAlloc[1][1]=Integer.parseInt(p22.getText());
        	currentAlloc[1][2]=Integer.parseInt(p23.getText());
        	currentAlloc[2][0]=Integer.parseInt(p31.getText());
        	currentAlloc[2][1]=Integer.parseInt(p32.getText());
        	currentAlloc[2][2]=Integer.parseInt(p33.getText());
        	currentAlloc[3][0]=Integer.parseInt(p41.getText());
        	currentAlloc[3][1]=Integer.parseInt(p42.getText());
        	currentAlloc[3][2]=Integer.parseInt(p43.getText());
        	currentAlloc[4][0]=Integer.parseInt(p51.getText());
        	currentAlloc[4][1]=Integer.parseInt(p52.getText());
        	currentAlloc[4][2]=Integer.parseInt(p53.getText());
		}
    	else if(nRes==4 && nProc==1)
    	{
    		availRes[0]=Integer.parseInt(r1.getText());
    		availRes[1]=Integer.parseInt(r2.getText());
    		availRes[2]=Integer.parseInt(r3.getText());
    		availRes[3]=Integer.parseInt(r4.getText());
    		needMatrix[0][0]=(Integer.parseInt(pm11.getText()))-(Integer.parseInt(p11.getText()));
        	needMatrix[0][1]=(Integer.parseInt(pm12.getText()))-(Integer.parseInt(p12.getText()));
        	needMatrix[0][2]=(Integer.parseInt(pm13.getText()))-(Integer.parseInt(p13.getText()));
        	needMatrix[0][3]=(Integer.parseInt(pm14.getText()))-(Integer.parseInt(p14.getText()));
        	currentAlloc[0][0]=Integer.parseInt(p11.getText());
        	currentAlloc[0][1]=Integer.parseInt(p12.getText());
        	currentAlloc[0][2]=Integer.parseInt(p13.getText());
        	currentAlloc[0][3]=Integer.parseInt(p14.getText());
    	}
    	else if(nRes==4 && nProc==2)
    	{
    		availRes[0]=Integer.parseInt(r1.getText());
    		availRes[1]=Integer.parseInt(r2.getText());
    		availRes[2]=Integer.parseInt(r3.getText());
    		availRes[3]=Integer.parseInt(r4.getText());
    		needMatrix[0][0]=(Integer.parseInt(pm11.getText()))-(Integer.parseInt(p11.getText()));
        	needMatrix[0][1]=(Integer.parseInt(pm12.getText()))-(Integer.parseInt(p12.getText()));
        	needMatrix[0][2]=(Integer.parseInt(pm13.getText()))-(Integer.parseInt(p13.getText()));
        	needMatrix[0][3]=(Integer.parseInt(pm14.getText()))-(Integer.parseInt(p14.getText()));
        	needMatrix[1][0]=(Integer.parseInt(pm21.getText()))-(Integer.parseInt(p21.getText()));
        	needMatrix[1][1]=(Integer.parseInt(pm22.getText()))-(Integer.parseInt(p22.getText()));
        	needMatrix[1][2]=(Integer.parseInt(pm23.getText()))-(Integer.parseInt(p23.getText()));
        	needMatrix[1][3]=(Integer.parseInt(pm24.getText()))-(Integer.parseInt(p24.getText()));
        	currentAlloc[0][0]=Integer.parseInt(p11.getText());
        	currentAlloc[0][1]=Integer.parseInt(p12.getText());
        	currentAlloc[0][2]=Integer.parseInt(p13.getText());
        	currentAlloc[0][3]=Integer.parseInt(p14.getText());
        	currentAlloc[1][0]=Integer.parseInt(p21.getText());
        	currentAlloc[1][1]=Integer.parseInt(p22.getText());
        	currentAlloc[1][2]=Integer.parseInt(p23.getText());
        	currentAlloc[1][3]=Integer.parseInt(p24.getText());
    	}
    	else if(nRes==4 && nProc==3)
    	{
    		availRes[0]=Integer.parseInt(r1.getText());
    		availRes[1]=Integer.parseInt(r2.getText());
    		availRes[2]=Integer.parseInt(r3.getText());
    		availRes[3]=Integer.parseInt(r4.getText());
    		needMatrix[0][0]=(Integer.parseInt(pm11.getText()))-(Integer.parseInt(p11.getText()));
        	needMatrix[0][1]=(Integer.parseInt(pm12.getText()))-(Integer.parseInt(p12.getText()));
        	needMatrix[0][2]=(Integer.parseInt(pm13.getText()))-(Integer.parseInt(p13.getText()));
        	needMatrix[0][3]=(Integer.parseInt(pm14.getText()))-(Integer.parseInt(p14.getText()));
        	needMatrix[1][0]=(Integer.parseInt(pm21.getText()))-(Integer.parseInt(p21.getText()));
        	needMatrix[1][1]=(Integer.parseInt(pm22.getText()))-(Integer.parseInt(p22.getText()));
        	needMatrix[1][2]=(Integer.parseInt(pm23.getText()))-(Integer.parseInt(p23.getText()));
        	needMatrix[1][3]=(Integer.parseInt(pm24.getText()))-(Integer.parseInt(p24.getText()));
        	needMatrix[2][0]=(Integer.parseInt(pm31.getText()))-(Integer.parseInt(p31.getText()));
        	needMatrix[2][1]=(Integer.parseInt(pm32.getText()))-(Integer.parseInt(p32.getText()));
        	needMatrix[2][2]=(Integer.parseInt(pm33.getText()))-(Integer.parseInt(p33.getText()));
        	needMatrix[2][3]=(Integer.parseInt(pm34.getText()))-(Integer.parseInt(p34.getText()));
        	currentAlloc[0][0]=Integer.parseInt(p11.getText());
        	currentAlloc[0][1]=Integer.parseInt(p12.getText());
        	currentAlloc[0][2]=Integer.parseInt(p13.getText());
        	currentAlloc[0][3]=Integer.parseInt(p14.getText());
        	currentAlloc[1][0]=Integer.parseInt(p21.getText());
        	currentAlloc[1][1]=Integer.parseInt(p22.getText());
        	currentAlloc[1][2]=Integer.parseInt(p23.getText());
        	currentAlloc[1][3]=Integer.parseInt(p24.getText());
        	currentAlloc[2][0]=Integer.parseInt(p31.getText());
        	currentAlloc[2][1]=Integer.parseInt(p32.getText());
        	currentAlloc[2][2]=Integer.parseInt(p33.getText());
        	currentAlloc[2][3]=Integer.parseInt(p34.getText());
        	
    	}
    	else if(nRes==4 && nProc==4)
    	{
    		availRes[0]=Integer.parseInt(r1.getText());
    		availRes[1]=Integer.parseInt(r2.getText());
    		availRes[2]=Integer.parseInt(r3.getText());
    		availRes[3]=Integer.parseInt(r4.getText());
    		needMatrix[0][0]=(Integer.parseInt(pm11.getText()))-(Integer.parseInt(p11.getText()));
        	needMatrix[0][1]=(Integer.parseInt(pm12.getText()))-(Integer.parseInt(p12.getText()));
        	needMatrix[0][2]=(Integer.parseInt(pm13.getText()))-(Integer.parseInt(p13.getText()));
        	needMatrix[0][3]=(Integer.parseInt(pm14.getText()))-(Integer.parseInt(p14.getText()));
        	needMatrix[1][0]=(Integer.parseInt(pm21.getText()))-(Integer.parseInt(p21.getText()));
        	needMatrix[1][1]=(Integer.parseInt(pm22.getText()))-(Integer.parseInt(p22.getText()));
        	needMatrix[1][2]=(Integer.parseInt(pm23.getText()))-(Integer.parseInt(p23.getText()));
        	needMatrix[1][3]=(Integer.parseInt(pm24.getText()))-(Integer.parseInt(p24.getText()));
        	needMatrix[2][0]=(Integer.parseInt(pm31.getText()))-(Integer.parseInt(p31.getText()));
        	needMatrix[2][1]=(Integer.parseInt(pm32.getText()))-(Integer.parseInt(p32.getText()));
        	needMatrix[2][2]=(Integer.parseInt(pm33.getText()))-(Integer.parseInt(p33.getText()));
        	needMatrix[2][3]=(Integer.parseInt(pm34.getText()))-(Integer.parseInt(p34.getText()));
        	needMatrix[3][0]=(Integer.parseInt(pm41.getText()))-(Integer.parseInt(p41.getText()));
        	needMatrix[3][1]=(Integer.parseInt(pm42.getText()))-(Integer.parseInt(p42.getText()));
        	needMatrix[3][2]=(Integer.parseInt(pm43.getText()))-(Integer.parseInt(p43.getText()));
        	needMatrix[3][3]=(Integer.parseInt(pm44.getText()))-(Integer.parseInt(p44.getText()));
        	currentAlloc[0][0]=Integer.parseInt(p11.getText());
        	currentAlloc[0][1]=Integer.parseInt(p12.getText());
        	currentAlloc[0][2]=Integer.parseInt(p13.getText());
        	currentAlloc[0][3]=Integer.parseInt(p14.getText());
        	currentAlloc[1][0]=Integer.parseInt(p21.getText());
        	currentAlloc[1][1]=Integer.parseInt(p22.getText());
        	currentAlloc[1][2]=Integer.parseInt(p23.getText());
        	currentAlloc[1][3]=Integer.parseInt(p24.getText());
        	currentAlloc[2][0]=Integer.parseInt(p31.getText());
        	currentAlloc[2][1]=Integer.parseInt(p32.getText());
        	currentAlloc[2][2]=Integer.parseInt(p33.getText());
        	currentAlloc[2][3]=Integer.parseInt(p34.getText());
        	currentAlloc[3][0]=Integer.parseInt(p41.getText());
        	currentAlloc[3][1]=Integer.parseInt(p42.getText());
        	currentAlloc[3][2]=Integer.parseInt(p43.getText());
        	currentAlloc[3][3]=Integer.parseInt(p44.getText());
    	}
    	else if(nRes==4 && nProc==5)
    	{
    		availRes[0]=Integer.parseInt(r1.getText());
    		availRes[1]=Integer.parseInt(r2.getText());
    		availRes[2]=Integer.parseInt(r3.getText());
    		availRes[3]=Integer.parseInt(r4.getText());
    		needMatrix[0][0]=(Integer.parseInt(pm11.getText()))-(Integer.parseInt(p11.getText()));
        	needMatrix[0][1]=(Integer.parseInt(pm12.getText()))-(Integer.parseInt(p12.getText()));
        	needMatrix[0][2]=(Integer.parseInt(pm13.getText()))-(Integer.parseInt(p13.getText()));
        	needMatrix[0][3]=(Integer.parseInt(pm14.getText()))-(Integer.parseInt(p14.getText()));
        	needMatrix[1][0]=(Integer.parseInt(pm21.getText()))-(Integer.parseInt(p21.getText()));
        	needMatrix[1][1]=(Integer.parseInt(pm22.getText()))-(Integer.parseInt(p22.getText()));
        	needMatrix[1][2]=(Integer.parseInt(pm23.getText()))-(Integer.parseInt(p23.getText()));
        	needMatrix[1][3]=(Integer.parseInt(pm24.getText()))-(Integer.parseInt(p24.getText()));
        	needMatrix[2][0]=(Integer.parseInt(pm31.getText()))-(Integer.parseInt(p31.getText()));
        	needMatrix[2][1]=(Integer.parseInt(pm32.getText()))-(Integer.parseInt(p32.getText()));
        	needMatrix[2][2]=(Integer.parseInt(pm33.getText()))-(Integer.parseInt(p33.getText()));
        	needMatrix[2][3]=(Integer.parseInt(pm34.getText()))-(Integer.parseInt(p34.getText()));
        	needMatrix[3][0]=(Integer.parseInt(pm41.getText()))-(Integer.parseInt(p41.getText()));
        	needMatrix[3][1]=(Integer.parseInt(pm42.getText()))-(Integer.parseInt(p42.getText()));
        	needMatrix[3][2]=(Integer.parseInt(pm43.getText()))-(Integer.parseInt(p43.getText()));
        	needMatrix[3][3]=(Integer.parseInt(pm44.getText()))-(Integer.parseInt(p44.getText()));
        	needMatrix[4][0]=(Integer.parseInt(pm51.getText()))-(Integer.parseInt(p51.getText()));
        	needMatrix[4][1]=(Integer.parseInt(pm52.getText()))-(Integer.parseInt(p52.getText()));
        	needMatrix[4][2]=(Integer.parseInt(pm53.getText()))-(Integer.parseInt(p53.getText()));
        	needMatrix[4][3]=(Integer.parseInt(pm54.getText()))-(Integer.parseInt(p54.getText()));
        	currentAlloc[0][0]=Integer.parseInt(p11.getText());
        	currentAlloc[0][1]=Integer.parseInt(p12.getText());
        	currentAlloc[0][2]=Integer.parseInt(p13.getText());
        	currentAlloc[0][3]=Integer.parseInt(p14.getText());
        	currentAlloc[1][0]=Integer.parseInt(p21.getText());
        	currentAlloc[1][1]=Integer.parseInt(p22.getText());
        	currentAlloc[1][2]=Integer.parseInt(p23.getText());
        	currentAlloc[1][3]=Integer.parseInt(p24.getText());
        	currentAlloc[2][0]=Integer.parseInt(p31.getText());
        	currentAlloc[2][1]=Integer.parseInt(p32.getText());
        	currentAlloc[2][2]=Integer.parseInt(p33.getText());
        	currentAlloc[2][3]=Integer.parseInt(p34.getText());
        	currentAlloc[3][0]=Integer.parseInt(p41.getText());
        	currentAlloc[3][1]=Integer.parseInt(p42.getText());
        	currentAlloc[3][2]=Integer.parseInt(p43.getText());
        	currentAlloc[3][3]=Integer.parseInt(p44.getText());
        	currentAlloc[4][0]=Integer.parseInt(p51.getText());
        	currentAlloc[4][1]=Integer.parseInt(p52.getText());
        	currentAlloc[4][2]=Integer.parseInt(p53.getText());
        	currentAlloc[4][3]=Integer.parseInt(p54.getText());
    	}
    	else if(nRes==5 && nProc==1)
    	{
    		availRes[0]=Integer.parseInt(r1.getText());
    		availRes[1]=Integer.parseInt(r2.getText());
    		availRes[2]=Integer.parseInt(r3.getText());
    		availRes[3]=Integer.parseInt(r4.getText());
    		availRes[4]=Integer.parseInt(r5.getText());
    		needMatrix[0][0]=(Integer.parseInt(pm11.getText()))-(Integer.parseInt(p11.getText()));
        	needMatrix[0][1]=(Integer.parseInt(pm12.getText()))-(Integer.parseInt(p12.getText()));
        	needMatrix[0][2]=(Integer.parseInt(pm13.getText()))-(Integer.parseInt(p13.getText()));
        	needMatrix[0][3]=(Integer.parseInt(pm14.getText()))-(Integer.parseInt(p14.getText()));
        	needMatrix[0][4]=(Integer.parseInt(pm15.getText()))-(Integer.parseInt(p15.getText()));
        	currentAlloc[0][0]=Integer.parseInt(p11.getText());
        	currentAlloc[0][1]=Integer.parseInt(p12.getText());
        	currentAlloc[0][2]=Integer.parseInt(p13.getText());
        	currentAlloc[0][3]=Integer.parseInt(p14.getText());
        	currentAlloc[0][4]=Integer.parseInt(p15.getText());
    	}
    	else if(nRes==5 && nProc==2)
    	{
    		availRes[0]=Integer.parseInt(r1.getText());
    		availRes[1]=Integer.parseInt(r2.getText());
    		availRes[2]=Integer.parseInt(r3.getText());
    		availRes[3]=Integer.parseInt(r4.getText());
    		availRes[4]=Integer.parseInt(r5.getText());
    		needMatrix[0][0]=(Integer.parseInt(pm11.getText()))-(Integer.parseInt(p11.getText()));
        	needMatrix[0][1]=(Integer.parseInt(pm12.getText()))-(Integer.parseInt(p12.getText()));
        	needMatrix[0][2]=(Integer.parseInt(pm13.getText()))-(Integer.parseInt(p13.getText()));
        	needMatrix[0][3]=(Integer.parseInt(pm14.getText()))-(Integer.parseInt(p14.getText()));
        	needMatrix[0][4]=(Integer.parseInt(pm15.getText()))-(Integer.parseInt(p15.getText()));
        	
        	needMatrix[1][0]=(Integer.parseInt(pm21.getText()))-(Integer.parseInt(p21.getText()));
        	needMatrix[1][1]=(Integer.parseInt(pm22.getText()))-(Integer.parseInt(p22.getText()));
        	needMatrix[1][2]=(Integer.parseInt(pm23.getText()))-(Integer.parseInt(p23.getText()));
        	needMatrix[1][3]=(Integer.parseInt(pm24.getText()))-(Integer.parseInt(p24.getText()));
        	needMatrix[1][4]=(Integer.parseInt(pm25.getText()))-(Integer.parseInt(p25.getText()));
        	currentAlloc[0][0]=Integer.parseInt(p11.getText());
        	currentAlloc[0][1]=Integer.parseInt(p12.getText());
        	currentAlloc[0][2]=Integer.parseInt(p13.getText());
        	currentAlloc[0][3]=Integer.parseInt(p14.getText());
        	currentAlloc[0][4]=Integer.parseInt(p15.getText());
        	currentAlloc[1][0]=Integer.parseInt(p21.getText());
        	currentAlloc[1][1]=Integer.parseInt(p22.getText());
        	currentAlloc[1][2]=Integer.parseInt(p23.getText());
        	currentAlloc[1][3]=Integer.parseInt(p24.getText());
        	currentAlloc[1][4]=Integer.parseInt(p25.getText());
    	}
    	else if(nRes==5 && nProc==3)
    	{
    		availRes[0]=Integer.parseInt(r1.getText());
    		availRes[1]=Integer.parseInt(r2.getText());
    		availRes[2]=Integer.parseInt(r3.getText());
    		availRes[3]=Integer.parseInt(r4.getText());
    		availRes[4]=Integer.parseInt(r5.getText());
    		needMatrix[0][0]=(Integer.parseInt(pm11.getText()))-(Integer.parseInt(p11.getText()));
        	needMatrix[0][1]=(Integer.parseInt(pm12.getText()))-(Integer.parseInt(p12.getText()));
        	needMatrix[0][2]=(Integer.parseInt(pm13.getText()))-(Integer.parseInt(p13.getText()));
        	needMatrix[0][3]=(Integer.parseInt(pm14.getText()))-(Integer.parseInt(p14.getText()));
        	needMatrix[0][4]=(Integer.parseInt(pm15.getText()))-(Integer.parseInt(p15.getText()));
        	
        	needMatrix[1][0]=(Integer.parseInt(pm21.getText()))-(Integer.parseInt(p21.getText()));
        	needMatrix[1][1]=(Integer.parseInt(pm22.getText()))-(Integer.parseInt(p22.getText()));
        	needMatrix[1][2]=(Integer.parseInt(pm23.getText()))-(Integer.parseInt(p23.getText()));
        	needMatrix[1][3]=(Integer.parseInt(pm24.getText()))-(Integer.parseInt(p24.getText()));
        	needMatrix[1][4]=(Integer.parseInt(pm25.getText()))-(Integer.parseInt(p25.getText()));
        	
        	needMatrix[2][0]=(Integer.parseInt(pm31.getText()))-(Integer.parseInt(p31.getText()));
        	needMatrix[2][1]=(Integer.parseInt(pm32.getText()))-(Integer.parseInt(p32.getText()));
        	needMatrix[2][2]=(Integer.parseInt(pm33.getText()))-(Integer.parseInt(p33.getText()));
        	needMatrix[2][3]=(Integer.parseInt(pm34.getText()))-(Integer.parseInt(p34.getText()));
        	needMatrix[2][4]=(Integer.parseInt(pm35.getText()))-(Integer.parseInt(p35.getText()));
        	currentAlloc[0][0]=Integer.parseInt(p11.getText());
        	currentAlloc[0][1]=Integer.parseInt(p12.getText());
        	currentAlloc[0][2]=Integer.parseInt(p13.getText());
        	currentAlloc[0][3]=Integer.parseInt(p14.getText());
        	currentAlloc[0][4]=Integer.parseInt(p15.getText());
        	currentAlloc[1][0]=Integer.parseInt(p21.getText());
        	currentAlloc[1][1]=Integer.parseInt(p22.getText());
        	currentAlloc[1][2]=Integer.parseInt(p23.getText());
        	currentAlloc[1][3]=Integer.parseInt(p24.getText());
        	currentAlloc[1][4]=Integer.parseInt(p25.getText());
        	currentAlloc[2][0]=Integer.parseInt(p31.getText());
        	currentAlloc[2][1]=Integer.parseInt(p32.getText());
        	currentAlloc[2][2]=Integer.parseInt(p33.getText());
        	currentAlloc[2][3]=Integer.parseInt(p34.getText());
        	currentAlloc[2][4]=Integer.parseInt(p35.getText());
    	}
    	else if(nRes==5 && nProc==4)
    	{
    		availRes[0]=Integer.parseInt(r1.getText());
    		availRes[1]=Integer.parseInt(r2.getText());
    		availRes[2]=Integer.parseInt(r3.getText());
    		availRes[3]=Integer.parseInt(r4.getText());
    		availRes[4]=Integer.parseInt(r5.getText());
    		needMatrix[0][0]=(Integer.parseInt(pm11.getText()))-(Integer.parseInt(p11.getText()));
        	needMatrix[0][1]=(Integer.parseInt(pm12.getText()))-(Integer.parseInt(p12.getText()));
        	needMatrix[0][2]=(Integer.parseInt(pm13.getText()))-(Integer.parseInt(p13.getText()));
        	needMatrix[0][3]=(Integer.parseInt(pm14.getText()))-(Integer.parseInt(p14.getText()));
        	needMatrix[0][4]=(Integer.parseInt(pm15.getText()))-(Integer.parseInt(p15.getText()));
        	
        	needMatrix[1][0]=(Integer.parseInt(pm21.getText()))-(Integer.parseInt(p21.getText()));
        	needMatrix[1][1]=(Integer.parseInt(pm22.getText()))-(Integer.parseInt(p22.getText()));
        	needMatrix[1][2]=(Integer.parseInt(pm23.getText()))-(Integer.parseInt(p23.getText()));
        	needMatrix[1][3]=(Integer.parseInt(pm24.getText()))-(Integer.parseInt(p24.getText()));
        	needMatrix[1][4]=(Integer.parseInt(pm25.getText()))-(Integer.parseInt(p25.getText()));
        	
        	needMatrix[2][0]=(Integer.parseInt(pm31.getText()))-(Integer.parseInt(p31.getText()));
        	needMatrix[2][1]=(Integer.parseInt(pm32.getText()))-(Integer.parseInt(p32.getText()));
        	needMatrix[2][2]=(Integer.parseInt(pm33.getText()))-(Integer.parseInt(p33.getText()));
        	needMatrix[2][3]=(Integer.parseInt(pm34.getText()))-(Integer.parseInt(p34.getText()));
        	needMatrix[2][4]=(Integer.parseInt(pm35.getText()))-(Integer.parseInt(p35.getText()));
        	
        	needMatrix[3][0]=(Integer.parseInt(pm41.getText()))-(Integer.parseInt(p41.getText()));
        	needMatrix[3][1]=(Integer.parseInt(pm42.getText()))-(Integer.parseInt(p42.getText()));
        	needMatrix[3][2]=(Integer.parseInt(pm43.getText()))-(Integer.parseInt(p43.getText()));
        	needMatrix[3][3]=(Integer.parseInt(pm44.getText()))-(Integer.parseInt(p44.getText()));
        	needMatrix[3][4]=(Integer.parseInt(pm45.getText()))-(Integer.parseInt(p45.getText()));
        	currentAlloc[0][0]=Integer.parseInt(p11.getText());
        	currentAlloc[0][1]=Integer.parseInt(p12.getText());
        	currentAlloc[0][2]=Integer.parseInt(p13.getText());
        	currentAlloc[0][3]=Integer.parseInt(p14.getText());
        	currentAlloc[0][4]=Integer.parseInt(p15.getText());
        	currentAlloc[1][0]=Integer.parseInt(p21.getText());
        	currentAlloc[1][1]=Integer.parseInt(p22.getText());
        	currentAlloc[1][2]=Integer.parseInt(p23.getText());
        	currentAlloc[1][3]=Integer.parseInt(p24.getText());
        	currentAlloc[1][4]=Integer.parseInt(p25.getText());
        	currentAlloc[2][0]=Integer.parseInt(p31.getText());
        	currentAlloc[2][1]=Integer.parseInt(p32.getText());
        	currentAlloc[2][2]=Integer.parseInt(p33.getText());
        	currentAlloc[2][3]=Integer.parseInt(p34.getText());
        	currentAlloc[2][4]=Integer.parseInt(p35.getText());
        	currentAlloc[3][0]=Integer.parseInt(p41.getText());
        	currentAlloc[3][1]=Integer.parseInt(p42.getText());
        	currentAlloc[3][2]=Integer.parseInt(p43.getText());
        	currentAlloc[3][3]=Integer.parseInt(p44.getText());
        	currentAlloc[3][4]=Integer.parseInt(p45.getText());
    	}
    	else if(nRes==5 && nProc==5)
    	{
    		availRes[0]=Integer.parseInt(r1.getText());
    		availRes[1]=Integer.parseInt(r2.getText());
    		availRes[2]=Integer.parseInt(r3.getText());
    		availRes[3]=Integer.parseInt(r4.getText());
    		availRes[4]=Integer.parseInt(r5.getText());
    		needMatrix[0][0]=(Integer.parseInt(pm11.getText()))-(Integer.parseInt(p11.getText()));
        	needMatrix[0][1]=(Integer.parseInt(pm12.getText()))-(Integer.parseInt(p12.getText()));
        	needMatrix[0][2]=(Integer.parseInt(pm13.getText()))-(Integer.parseInt(p13.getText()));
        	needMatrix[0][3]=(Integer.parseInt(pm14.getText()))-(Integer.parseInt(p14.getText()));
        	needMatrix[0][4]=(Integer.parseInt(pm15.getText()))-(Integer.parseInt(p15.getText()));
        	
        	needMatrix[1][0]=(Integer.parseInt(pm21.getText()))-(Integer.parseInt(p21.getText()));
        	needMatrix[1][1]=(Integer.parseInt(pm22.getText()))-(Integer.parseInt(p22.getText()));
        	needMatrix[1][2]=(Integer.parseInt(pm23.getText()))-(Integer.parseInt(p23.getText()));
        	needMatrix[1][3]=(Integer.parseInt(pm24.getText()))-(Integer.parseInt(p24.getText()));
        	needMatrix[1][4]=(Integer.parseInt(pm25.getText()))-(Integer.parseInt(p25.getText()));
        	
        	needMatrix[2][0]=(Integer.parseInt(pm31.getText()))-(Integer.parseInt(p31.getText()));
        	needMatrix[2][1]=(Integer.parseInt(pm32.getText()))-(Integer.parseInt(p32.getText()));
        	needMatrix[2][2]=(Integer.parseInt(pm33.getText()))-(Integer.parseInt(p33.getText()));
        	needMatrix[2][3]=(Integer.parseInt(pm34.getText()))-(Integer.parseInt(p34.getText()));
        	needMatrix[2][4]=(Integer.parseInt(pm35.getText()))-(Integer.parseInt(p35.getText()));
        	
        	needMatrix[3][0]=(Integer.parseInt(pm41.getText()))-(Integer.parseInt(p41.getText()));
        	needMatrix[3][1]=(Integer.parseInt(pm42.getText()))-(Integer.parseInt(p42.getText()));
        	needMatrix[3][2]=(Integer.parseInt(pm43.getText()))-(Integer.parseInt(p43.getText()));
        	needMatrix[3][3]=(Integer.parseInt(pm44.getText()))-(Integer.parseInt(p44.getText()));
        	needMatrix[3][4]=(Integer.parseInt(pm45.getText()))-(Integer.parseInt(p45.getText()));
        	
        	needMatrix[4][0]=(Integer.parseInt(pm51.getText()))-(Integer.parseInt(p51.getText()));
        	needMatrix[4][1]=(Integer.parseInt(pm52.getText()))-(Integer.parseInt(p52.getText()));
        	needMatrix[4][2]=(Integer.parseInt(pm53.getText()))-(Integer.parseInt(p53.getText()));
        	needMatrix[4][3]=(Integer.parseInt(pm54.getText()))-(Integer.parseInt(p54.getText()));
        	needMatrix[4][4]=(Integer.parseInt(pm55.getText()))-(Integer.parseInt(p55.getText()));
        	
        	currentAlloc[0][0]=Integer.parseInt(p11.getText());
        	currentAlloc[0][1]=Integer.parseInt(p12.getText());
        	currentAlloc[0][2]=Integer.parseInt(p13.getText());
        	currentAlloc[0][3]=Integer.parseInt(p14.getText());
        	currentAlloc[0][4]=Integer.parseInt(p15.getText());
        	currentAlloc[1][0]=Integer.parseInt(p21.getText());
        	currentAlloc[1][1]=Integer.parseInt(p22.getText());
        	currentAlloc[1][2]=Integer.parseInt(p23.getText());
        	currentAlloc[1][3]=Integer.parseInt(p24.getText());
        	currentAlloc[1][4]=Integer.parseInt(p25.getText());
        	currentAlloc[2][0]=Integer.parseInt(p31.getText());
        	currentAlloc[2][1]=Integer.parseInt(p32.getText());
        	currentAlloc[2][2]=Integer.parseInt(p33.getText());
        	currentAlloc[2][3]=Integer.parseInt(p34.getText());
        	currentAlloc[2][4]=Integer.parseInt(p35.getText());
        	currentAlloc[3][0]=Integer.parseInt(p41.getText());
        	currentAlloc[3][1]=Integer.parseInt(p42.getText());
        	currentAlloc[3][2]=Integer.parseInt(p43.getText());
        	currentAlloc[3][3]=Integer.parseInt(p44.getText());
        	currentAlloc[3][4]=Integer.parseInt(p45.getText());
        	currentAlloc[4][0]=Integer.parseInt(p51.getText());
        	currentAlloc[4][1]=Integer.parseInt(p52.getText());
        	currentAlloc[4][2]=Integer.parseInt(p53.getText());
        	currentAlloc[4][3]=Integer.parseInt(p54.getText());
        	currentAlloc[4][4]=Integer.parseInt(p55.getText());
    	}
    	
    }
}
