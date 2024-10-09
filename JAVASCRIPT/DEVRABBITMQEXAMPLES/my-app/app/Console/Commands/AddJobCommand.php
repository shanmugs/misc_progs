<?php

namespace App\Console\Commands;

use Illuminate\Console\Command;
use App\Jobs\SimpleConsumer;

class AddJobCommand extends Command
{
    /**
     * The name and signature of the console command.
     *
     * @var string
     */
    protected $signature = 'add:job';

    /**
     * The console command description.
     *
     * @var string
     */
    protected $description = 'Command description';

    /**
     * Execute the console command.
     *
     * @return int
     */
    public function handle()
    {
        SimpleConsumer::dispatch();

        return 0;
    }
}
