dtmc

module service
    state : [0..3] init 0;
    [] state=0 -> 0.9:(state'=1) + 0.1:(state'=3);
    [] state=1 -> 0.8:(state'=2) + 0.2:(state'=3);
    [] state=2 -> (state'=3);
endmodule
